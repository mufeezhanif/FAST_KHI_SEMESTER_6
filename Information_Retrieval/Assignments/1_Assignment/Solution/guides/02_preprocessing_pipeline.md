# Preprocessing Pipeline Guide

This part is worth marks directly. Build it carefully.

Important: this version uses a from-scratch approach with pure Python logic.

## What to Implement
- Read all speech files.
- Normalize text (lowercase).
- Tokenize.
- Remove stopwords.
- Apply stemming.

## Suggested File: `src/preprocessing.py`

```python
from typing import List, Set


def load_stopwords(path: str) -> Set[str]:
    stopwords = set()
    with open(path, "r", encoding="utf-8") as f:
        for line in f:
            word = line.strip().lower()
            if word:
                stopwords.add(word)
    return stopwords


def tokenize(text: str) -> List[str]:
    # Manual tokenizer: keep only alphanumeric sequences.
    text = text.lower()
    tokens = []
    current = []

    for ch in text:
        if ("a" <= ch <= "z") or ("0" <= ch <= "9"):
            current.append(ch)
        else:
            if current:
                tokens.append("".join(current))
                current = []

    if current:
        tokens.append("".join(current))

    return tokens


def stem_word(word: str) -> str:
    # Lightweight custom stemmer (rule-based suffix stripping).
    # This is intentionally simple and educational.
    if len(word) <= 3:
        return word

    rules = [
        ("ingly", ""),
        ("edly", ""),
        ("ing", ""),
        ("ed", ""),
        ("ies", "y"),
        ("sses", "ss"),
        ("s", ""),
        ("ly", ""),
        ("ment", ""),
    ]

    for suffix, replacement in rules:
        if word.endswith(suffix) and len(word) > len(suffix) + 2:
            return word[: -len(suffix)] + replacement

    return word


def normalize_tokens(tokens: List[str], stopwords: Set[str]) -> List[str]:
    cleaned = []
    for tok in tokens:
        if tok in stopwords:
            continue
        cleaned.append(stem_word(tok))
    return cleaned


def preprocess_text(text: str, stopwords: Set[str]) -> List[str]:
    tokens = tokenize(text)
    return normalize_tokens(tokens, stopwords)
```

## Important Design Decision
Use **same preprocessing for both documents and query terms**.
If documents are stemmed but query terms are not stemmed, retrieval will fail.

Because you are implementing stemming manually, make sure query terms also go through `stem_word()`.

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
- Over-aggressive stemming rules that cut too much text.

## Suggested Improvement (Optional)
Create a `debug_stemming_examples()` function and print examples:

```python
def debug_stemming_examples():
    words = ["running", "wanted", "actions", "policies", "government"]
    for w in words:
        print(w, "->", stem_word(w))
```
