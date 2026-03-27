# Testing, Evaluation, and Bonus Guide

## 1) Test Strategy

Test in three levels:
- Unit tests for preprocessing and set operations
- Module tests for index build/load
- End-to-end query tests from provided query list

## 2) Quick Manual Test Script
Create `src/evaluate.py`:

```python
def print_result(name: str, result):
    print("=" * 60)
    print(name)
    print(sorted(result))


def run_demo_tests(engine):
    print_result("actions AND wanted", engine.search_boolean("actions AND wanted"))
    print_result("united OR plane", engine.search_boolean("united OR plane"))
    print_result("biggest AND ( near OR box )", engine.search_complex("biggest AND ( near OR box )"))
    print_result("after years /1", engine.search_proximity("after years /1"))
```

## 3) Grading Rubric Mapping
- Preprocessing (2): show pipeline and examples.
- Inverted + Positional indexes (2): show code + persisted files.
- Simple Boolean (2): demonstrate with query examples.
- Complex Boolean (2): demonstrate parentheses and NOT.
- Proximity (2): show `/k` query outputs.
- Bonus GUI (2): smooth Streamlit demo with user-friendly design.
- Clean code bonus (2): modular files, clear function names, comments.

## 4) Performance Logging (Good Impression)
Print index build time and query execution time.

```python
import time

t0 = time.perf_counter()
# build indexes
build_ms = (time.perf_counter() - t0) * 1000
print(f"Index build time: {build_ms:.2f} ms")
```

## 5) What to Show During Viva/Demo
1. Open app.
2. Run one query for each type.
3. Show parsed tokens/postfix for complex query.
4. Show positional match logic for one proximity query.
5. Show saved index files and how reload avoids rebuild.

## 6) Final Quality Checklist
- No crashes on invalid input.
- Meaningful error messages.
- Consistent preprocessing for documents and queries.
- Index files are created and loaded successfully.
- Output doc IDs are sorted and readable.
