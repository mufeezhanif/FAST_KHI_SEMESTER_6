# Stemming From Scratch Guide (Basic + Porter-Like)

This guide is only about stemming logic, implemented with pure Python loops and conditions.

## Learning Path
1. Implement `basic_stem_word` first.
2. Test on 10-20 words.
3. Implement helper functions for Porter-like behavior.
4. Implement Porter-like Step 1a, 1b, 1c.
5. Compare outputs between basic and porter-like.

## Step 1: Basic Stemmer

```python
def basic_stem_word(word: str) -> str:
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
```

## Step 2: Porter-Like Helpers

```python
def is_consonant(word: str, i: int) -> bool:
    ch = word[i]
    if ch in "aeiou":
        return False
    if ch == "y":
        if i == 0:
            return True
        return not is_consonant(word, i - 1)
    return True


def contains_vowel(stem: str) -> bool:
    for i in range(len(stem)):
        if not is_consonant(stem, i):
            return True
    return False


def measure(word: str) -> int:
    m = 0
    i = 0
    n = len(word)
    while i < n:
        while i < n and is_consonant(word, i):
            i += 1
        while i < n and not is_consonant(word, i):
            i += 1
        if i < n:
            m += 1
    return m


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
```

## Step 3: Porter-Like Step 1

```python
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
    if len(word) <= 2:
        return word
    word = porter_step_1a(word)
    word = porter_step_1b(word)
    word = porter_step_1c(word)
    return word
```

## Step 4: Compare Outputs

```python
def compare_stemmers():
    words = [
        "running", "agreed", "studies", "classes", "happiness",
        "relational", "flying", "crying", "wanted", "actions",
    ]
    for w in words:
        b = basic_stem_word(w)
        p = porter_like_stem_word(w)
        print(f"{w:12} basic={b:10} porter_like={p}")
```

## Usage Rule
- Use one mode consistently across all indexing and query processing.
- Do not mix basic for docs and porter-like for query (or vice versa).

## Practical Recommendation
- Start your project with `basic` mode.
- Switch to `porter_like` once core retrieval is stable.
