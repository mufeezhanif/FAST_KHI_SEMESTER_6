# Preprocessing Pipeline Guide

This part is worth marks directly. Build it carefully.

## What to Implement
- Read all speech files.
- Normalize text (lowercase).
- Tokenize.
- Remove stopwords.
- Apply stemming.

## Suggested File: `src/preprocessing.py`

```python
import re
from typing import List, Set
from nltk.stem import PorterStemmer

stemmer = PorterStemmer()


def load_stopwords(path: str) -> Set[str]:
    stopwords = set()
    with open(path, "r", encoding="utf-8") as f:
        for line in f:
            word = line.strip().lower()
            if word:
                stopwords.add(word)
    return stopwords


def tokenize(text: str) -> List[str]:
    # Keep alphabetic and numeric words; split punctuation.
    return re.findall(r"[a-zA-Z0-9]+", text.lower())


def normalize_tokens(tokens: List[str], stopwords: Set[str]) -> List[str]:
    cleaned = []
    for tok in tokens:
        if tok in stopwords:
            continue
        cleaned.append(stemmer.stem(tok))
    return cleaned


def preprocess_text(text: str, stopwords: Set[str]) -> List[str]:
    tokens = tokenize(text)
    return normalize_tokens(tokens, stopwords)
```

## Important Design Decision
Use **same preprocessing for both documents and query terms**.
If documents are stemmed but query terms are not stemmed, retrieval will fail.

## Suggested File: `src/io_utils.py`

```python
import os
from typing import Dict


def load_documents(folder_path: str) -> Dict[str, str]:
    docs = {}
    for fname in sorted(os.listdir(folder_path)):
        if not fname.endswith(".txt"):
            continue
        doc_id = fname.replace("speech_", "").replace(".txt", "")
        with open(os.path.join(folder_path, fname), "r", encoding="utf-8") as f:
            docs[doc_id] = f.read()
    return docs
```

## Quick Self-Test
After preprocessing one document, print:
- first 20 tokens
- total tokens before vs after cleaning

```python
raw = docs["0"]
raw_tokens = tokenize(raw)
clean = preprocess_text(raw, stopwords)
print(len(raw_tokens), len(clean), clean[:20])
```

## Common Mistakes
- Forgetting to strip lines while reading stopwords.
- Using different tokenization for query and documents.
- Not converting to lowercase consistently.
