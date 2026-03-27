# Project Setup Guide (Python + Streamlit)

## 1) Create Environment

```bash
python3 -m venv .venv
source .venv/bin/activate
pip install --upgrade pip
```

## 2) Install Libraries

Use these beginner-friendly libraries:
- `streamlit`: GUI
- `nltk`: stemming and token helpers

```bash
pip install streamlit nltk
```

## 3) requirements.txt

```txt
streamlit==1.44.1
nltk==3.9.1
```

## 4) Essential Python Concepts You Will Use
- `dict`: for indexes (`term -> posting list`)
- `set`: for Boolean operations (`&`, `|`, `-`)
- `list`: token sequence and positions
- functions with type hints

Example:

```python
from typing import Dict, List, Set

index: Dict[str, Set[str]] = {}
tokens: List[str] = ["hello", "world"]
```

## 5) Create Base Files
Create files in `src/` exactly as listed in the sequence document.

## 6) Run App

```bash
streamlit run app.py
```

## 7) Good Practices for Assignment Marks
- Keep functions small and focused.
- Add clear comments only where logic is non-obvious.
- Save indexes to disk and load them (required by rubric).
