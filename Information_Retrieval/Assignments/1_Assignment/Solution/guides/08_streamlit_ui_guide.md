# Streamlit UI Guide (Bonus Marks Focus)

This guide targets the bonus GUI marks with a clean and friendly interface.

## UI Features You Should Include

Must-have features:
- Query input box
- Query type selector: Simple Boolean, Complex Boolean, Proximity
- Search button
- Result panel showing doc IDs
- Result count

Bonus-friendly features:
- Sidebar showing preprocessing options used (lowercase, stopword removal, custom stemming)
- Expandable panel to show parsed tokens and postfix expression
- Display matched snippets (first 1-2 lines from each doc)
- Timing info (query execution in milliseconds)
- "Load/Rebuild Index" controls
- Example query buttons (from provided query list)

## Suggested `app.py` Structure

```python
import time
import streamlit as st


def main():
    st.set_page_config(page_title="Boolean IR System", layout="wide")
    st.title("Boolean Information Retrieval on Trump Speeches")

    with st.sidebar:
        st.header("Index Controls")
        rebuild = st.button("Rebuild Indexes")
        st.markdown("### Preprocessing")
        st.write("- Case Folding")
        st.write("- Stopword Removal")
        st.write("- Custom Rule-Based Stemming")

    query_type = st.selectbox(
        "Select Query Type",
        ["Simple Boolean", "Complex Boolean", "Proximity"],
    )
    query_text = st.text_input("Enter Query", value="actions AND wanted")

    if st.button("Search"):
        t0 = time.perf_counter()
        # route query to relevant engine
        # results = ...
        elapsed = (time.perf_counter() - t0) * 1000

        st.subheader("Results")
        # st.write(sorted(results))
        st.caption(f"Execution time: {elapsed:.2f} ms")


if __name__ == "__main__":
    main()
```

## Suggested UI Components by Section

1. Header section
- Assignment title
- Short description of supported query formats

2. Search section
- Query type dropdown
- Query input
- Search button

3. Explainability section (for better impression)
- Parsed query tokens
- Normalized terms
- Postfix expression (for complex queries)

4. Results section
- Total matched documents
- Document IDs sorted
- Optional preview text per document

5. Footer section
- Team/member names
- Date/version

## Example Query Placeholders
- `actions AND wanted`
- `united OR plane`
- `biggest AND ( near OR box )`
- `after years /1`

## Common UI Mistakes
- UI directly rebuilding index on every search (slow)
- No clear error messages for invalid query format
- Not showing what query type is currently active
