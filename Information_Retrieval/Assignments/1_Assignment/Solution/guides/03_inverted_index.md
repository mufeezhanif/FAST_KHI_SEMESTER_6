# Inverted Index Guide

## What is Inverted Index?
A mapping from term to documents containing that term:

```python
{
  "economi": {"0", "5", "19"},
  "america": {"2", "8", "10"}
}
```

## Why it matters
This powers Boolean queries efficiently.

## Suggested File: `src/index_inverted.py`

```python
import json
from collections import defaultdict
from typing import Dict, List, Set


def build_inverted_index(doc_tokens: Dict[str, List[str]]) -> Dict[str, Set[str]]:
    index = defaultdict(set)
    for doc_id, tokens in doc_tokens.items():
        for term in set(tokens):  # set() avoids duplicates from same doc
            index[term].add(doc_id)
    return dict(index)


def save_inverted_index(index: Dict[str, Set[str]], path: str) -> None:
    serializable = {term: sorted(list(doc_ids)) for term, doc_ids in index.items()}
    with open(path, "w", encoding="utf-8") as f:
        json.dump(serializable, f, indent=2)


def load_inverted_index(path: str) -> Dict[str, Set[str]]:
    with open(path, "r", encoding="utf-8") as f:
        raw = json.load(f)
    return {term: set(doc_ids) for term, doc_ids in raw.items()}
```

## Driver Snippet

```python
from src.io_utils import load_documents
from src.preprocessing import load_stopwords, preprocess_text
from src.index_inverted import build_inverted_index, save_inverted_index

docs = load_documents("data/Trump Speechs")
stopwords = load_stopwords("data/Stopword-List.txt")

doc_tokens = {doc_id: preprocess_text(text, stopwords) for doc_id, text in docs.items()}
inverted = build_inverted_index(doc_tokens)
save_inverted_index(inverted, "indexes/inverted_index.json")
print("Terms indexed:", len(inverted))
```

## Marking Alignment
- “Formation of Inverted Index” -> done
- “Saving and loading indexes” -> done via JSON save/load

## Validation
Check one known term:

```python
print("united" in inverted)
print(sorted(list(inverted.get("unit", set())))[:10])  # if stemmed with Porter
```
