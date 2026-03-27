# Positional Index Guide

## What is Positional Index?
It stores where a term appears in each document.

Structure:

```python
{
  "america": {
    "2": [4, 17, 53],
    "10": [8, 91]
  }
}
```

## Why needed?
For proximity queries like `after years /1`.

## Suggested File: `src/index_positional.py`

```python
import json
from collections import defaultdict
from typing import Dict, List


def build_positional_index(doc_tokens: Dict[str, List[str]]) -> Dict[str, Dict[str, List[int]]]:
    index = defaultdict(lambda: defaultdict(list))

    for doc_id, tokens in doc_tokens.items():
        for pos, term in enumerate(tokens):
            index[term][doc_id].append(pos)

    # Convert nested defaultdicts to normal dicts
    return {term: dict(postings) for term, postings in index.items()}


def save_positional_index(index: Dict[str, Dict[str, List[int]]], path: str) -> None:
    with open(path, "w", encoding="utf-8") as f:
        json.dump(index, f, indent=2)


def load_positional_index(path: str) -> Dict[str, Dict[str, List[int]]]:
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)
```

## Sanity Checks

```python
pos_idx = build_positional_index(doc_tokens)
print("Total terms:", len(pos_idx))
some_term = next(iter(pos_idx))
print("Sample term:", some_term, "->", list(pos_idx[some_term].items())[:1])
```

## Common Mistakes
- Storing string positions instead of integers.
- Building index from raw tokens instead of preprocessed tokens.
