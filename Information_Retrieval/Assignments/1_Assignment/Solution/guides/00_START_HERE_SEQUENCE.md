# Assignment 1 Implementation Roadmap (Read This First)

This file tells you the exact order to follow.

## Goal
Build a Boolean Information Retrieval system over the Trump speeches dataset with:
- Preprocessing (tokenization, case folding, stopword removal, stemming)
- Inverted index
- Positional index
- Simple and complex Boolean query processing
- Proximity query processing (`term1 term2 /k`)
- Streamlit GUI (bonus)

## Recommended Order
1. Read `guides/01_project_setup.md`
2. Implement preprocessing from `guides/02_preprocessing_pipeline.md`
3. Build and persist inverted index from `guides/03_inverted_index.md`
4. Build and persist positional index from `guides/04_positional_index.md`
5. Implement simple Boolean retrieval from `guides/05_boolean_retrieval.md`
6. Implement complex Boolean retrieval from `guides/06_complex_boolean_queries.md`
7. Implement proximity queries from `guides/07_proximity_queries.md`
8. Build Streamlit GUI from `guides/08_streamlit_ui_guide.md`
9. Evaluate against provided queries using `guides/09_testing_evaluation_bonus.md`
10. Final polish and submission from `guides/10_submission_checklist.md`

## Suggested Project Structure
Create this structure before coding:

```text
Solution/
  app.py
  requirements.txt
  data/
    Trump Speechs/
    Stopword-List.txt
    Querry List.txt
  indexes/
    inverted_index.json
    positional_index.json
    doc_id_map.json
  src/
    __init__.py
    config.py
    io_utils.py
    preprocessing.py
    index_inverted.py
    index_positional.py
    query_parser.py
    retrieval_boolean.py
    retrieval_proximity.py
    evaluate.py
```

## Minimum Viable Milestones
- Milestone 1: Preprocessing + index building works.
- Milestone 2: Simple Boolean queries return correct doc IDs.
- Milestone 3: Complex Boolean queries with parentheses work.
- Milestone 4: Proximity query logic works.
- Milestone 5: Streamlit UI demonstrates all query types and outputs.

## Study Strategy (Because you are learning Python)
- Build one function at a time.
- Print intermediate outputs often.
- After each function, test with 1-2 tiny examples.
- Do not jump to GUI before core retrieval works.
