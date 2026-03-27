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

Use this complete, runnable version in your own code file.

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


def basic_stem_word(word: str) -> str:
    # Simple custom stemmer: good first step for learning.
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


def is_consonant(word: str, i: int) -> bool:
    ch = word[i]
    if ch in "aeiou":
        return False
    if ch == "y":
        if i == 0:
            return True
        return not is_consonant(word, i - 1)
    return True


def measure(word: str) -> int:
    # Porter measure m: number of VC sequences.
    m = 0
    i = 0
    n = len(word)

    while i < n:
        while i < n and is_consonant(word, i):
            i += 1
        while i < n and not is_consonant(word, i):
            i += 1
            if i <= n:
                pass
        if i < n:
            m += 1
    return m


def contains_vowel(stem: str) -> bool:
    for i in range(len(stem)):
        if not is_consonant(stem, i):
            return True
    return False


def ends_with_double_consonant(word: str) -> bool:
    if len(word) < 2:
        return False
    return word[-1] == word[-2] and is_consonant(word, len(word) - 1)


def cvc(word: str) -> bool:
    if len(word) < 3:
        return False
    c1 = is_consonant(word, len(word) - 3)
    v = not is_consonant(word, len(word) - 2)
    c2 = is_consonant(word, len(word) - 1)
    if not (c1 and v and c2):
        return False
    return word[-1] not in "wxy"


def porter_step_1a(word: str) -> str:
    if word.endswith("sses"):
        return word[:-2]
    if word.endswith("ies"):
        return word[:-2]
    if word.endswith("ss"):
        return word
    if word.endswith("s"):
        return word[:-1]
    return word


def porter_step_1b(word: str) -> str:
    if word.endswith("eed"):
        stem = word[:-3]
        if measure(stem) > 0:
            return stem + "ee"
        return word

    changed = False
    stem = ""

    if word.endswith("ed"):
        stem = word[:-2]
        if contains_vowel(stem):
            word = stem
            changed = True
    elif word.endswith("ing"):
        stem = word[:-3]
        if contains_vowel(stem):
            word = stem
            changed = True

    if changed:
        if word.endswith("at") or word.endswith("bl") or word.endswith("iz"):
            word += "e"
        elif ends_with_double_consonant(word) and word[-1] not in "lsz":
            word = word[:-1]
        elif measure(word) == 1 and cvc(word):
            word += "e"
    return word


def porter_step_1c(word: str) -> str:
    if word.endswith("y"):
        stem = word[:-1]
        if contains_vowel(stem):
            return stem + "i"
    return word


def porter_like_stem_word(word: str) -> str:
    # Educational Porter-like stemmer (covers Step 1a, 1b, 1c).
    # This is usually enough for Assignment-1 Boolean matching consistency.
    if len(word) <= 2:
        return word

    w = word
    w = porter_step_1a(w)
    w = porter_step_1b(w)
    w = porter_step_1c(w)
    return w


def normalize_tokens(tokens: List[str], stopwords: Set[str], stem_mode: str = "basic") -> List[str]:
    cleaned = []

    if stem_mode == "porter_like":
        stem_fn = porter_like_stem_word
    else:
        stem_fn = basic_stem_word

    for tok in tokens:
        if tok in stopwords:
            continue
        cleaned.append(stem_fn(tok))
    return cleaned


def preprocess_text(text: str, stopwords: Set[str], stem_mode: str = "basic") -> List[str]:
    tokens = tokenize(text)
    return normalize_tokens(tokens, stopwords, stem_mode=stem_mode)
```

## Important Design Decision
Use **same preprocessing for both documents and query terms**.
If documents are stemmed but query terms are not stemmed, retrieval will fail.

Because you are implementing stemming manually, make sure query terms also go through the same stemmer (`basic` or `porter_like`) used for documents.

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
    print(w, "basic ->", basic_stem_word(w), "porter_like ->", porter_like_stem_word(w))
```

## Which Stemmer Should You Use?
- For step-by-step coding and easier debugging: start with `basic`.
- For better normalization quality with pure-Python logic: use `porter_like`.

## Zero-Error Integration Checklist
Before moving to indexing:
- Ensure `preprocess_text` is called with same `stem_mode` for docs and queries.
- Run `debug_stemming_examples()` once.
- Print token counts for one document to confirm pipeline output.
