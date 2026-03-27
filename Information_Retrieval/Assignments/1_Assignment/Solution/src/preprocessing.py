from typing import List, Set

def load_stopwords(path: str) -> Set[str]:
    stopwords = set()
    with open(path, "r", encoding='utf-8') as f:
        for line in f:
            word = line.strip().lower()
            if word:
                stopwords.add(word)
    return stopwords

def tokenize(text:str)-> List[str]:
    text = text.lower()
    tokens = []
    current = []
    
    for ch in text:
        if ("a" <=ch <="z") or ("0"<=ch <="9"):
            current.append(ch)
        else:
            if current:
                tokens.append("".join(current))
                current = []
    if current:
        tokens.append("".join(current))
        
    return tokens

def stem_word(word: str)-> str:
    if len(word)<=3:
        return word
    
    rules 