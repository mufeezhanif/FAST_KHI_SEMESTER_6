# Complex Boolean Queries Guide

You need to handle forms like:
- `biggest AND ( near OR box )`
- `NOT ( united AND plane )`

## Recommended Approach
1. Tokenize query into terms/operators/parentheses.
2. Convert infix to postfix (Reverse Polish) using shunting-yard.
3. Evaluate postfix with a stack of sets.

## Suggested File: `src/query_parser.py`

```python
import re
from typing import List

OPERATORS = {"AND", "OR", "NOT"}
PRECEDENCE = {"NOT": 3, "AND": 2, "OR": 1}


def tokenize_boolean_query(query: str) -> List[str]:
    # Captures parentheses and words
    raw = re.findall(r"\(|\)|[A-Za-z0-9_]+", query)
    tokens = []
    for t in raw:
        up = t.upper()
        tokens.append(up if up in OPERATORS else t)
    return tokens


def infix_to_postfix(tokens: List[str]) -> List[str]:
    output = []
    stack = []

    for tok in tokens:
        if tok == "(":
            stack.append(tok)
        elif tok == ")":
            while stack and stack[-1] != "(":
                output.append(stack.pop())
            stack.pop()  # pop '('
        elif tok in OPERATORS:
            while stack and stack[-1] in OPERATORS and PRECEDENCE[stack[-1]] >= PRECEDENCE[tok]:
                output.append(stack.pop())
            stack.append(tok)
        else:
            output.append(tok)

    while stack:
        output.append(stack.pop())

    return output
```

## Suggested Evaluation Logic
Add in `src/retrieval_boolean.py`:

```python
from typing import Dict, Set, List


def eval_postfix(postfix: List[str], inverted_index: Dict[str, Set[str]], all_docs: Set[str], normalize_term_fn) -> Set[str]:
    stack = []

    for tok in postfix:
        if tok == "NOT":
            a = stack.pop()
            stack.append(all_docs - a)
        elif tok in {"AND", "OR"}:
            right = stack.pop()
            left = stack.pop()
            stack.append(left & right if tok == "AND" else left | right)
        else:
            term = normalize_term_fn(tok)
            stack.append(inverted_index.get(term, set()))

    if len(stack) != 1:
        raise ValueError("Invalid query")
    return stack[0]
```

## Error Handling (Important for UI)
Show clear messages for:
- Unbalanced parentheses
- Empty query
- Unknown operator order
