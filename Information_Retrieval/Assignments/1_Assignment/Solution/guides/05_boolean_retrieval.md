# Simple Boolean Retrieval Guide

Simple queries to support first:
- `x AND y`
- `x OR y`
- `NOT x`
- `x AND y AND z`

## Core Idea
Use sets:
- AND = intersection (`&`)
- OR = union (`|`)
- NOT = complement (`all_docs - x_docs`)

## Suggested File: `src/retrieval_boolean.py`

```python
from typing import Dict, Set, List


def get_postings(term: str, inverted_index: Dict[str, Set[str]]) -> Set[str]:
    return inverted_index.get(term, set())


def eval_simple_boolean(query_tokens: List[str], inverted_index: Dict[str, Set[str]], all_docs: Set[str]) -> Set[str]:
    # Supports forms like ["x", "AND", "y"] or ["NOT", "x"]
    if len(query_tokens) == 2 and query_tokens[0] == "NOT":
        term_docs = get_postings(query_tokens[1], inverted_index)
        return all_docs - term_docs

    if len(query_tokens) == 3:
        left, op, right = query_tokens
        a = get_postings(left, inverted_index)
        b = get_postings(right, inverted_index)
        if op == "AND":
            return a & b
        if op == "OR":
            return a | b

    if len(query_tokens) == 5:
        # x AND y AND z (left-associative)
        first = eval_simple_boolean(query_tokens[:3], inverted_index, all_docs)
        second_term = query_tokens[4]
        op = query_tokens[3]
        second = get_postings(second_term, inverted_index)
        return first & second if op == "AND" else first | second

    raise ValueError("Unsupported simple query format")
```

## Query Preprocessing Rule
Before lookup, preprocess each term exactly like document preprocessing.

Example helper:

```python
def normalize_query_term(term: str, stopwords: set, preprocess_text_fn) -> str:
    toks = preprocess_text_fn(term, stopwords)
    return toks[0] if toks else ""
```

## Quick Test

```python
all_docs = set(docs.keys())
q = ["action", "AND", "want"]  # use normalized/stemmed terms
result = eval_simple_boolean(q, inverted, all_docs)
print(sorted(result))
```
