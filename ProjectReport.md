# Language Identification Tool Project Report

## Introduction

The language identification tool is a tool for identifying the language of a given text. It compares the text to texts in the Albanian and English languages using n-grams (sequences of n consecutive words) and determines which language the text is most similar to.

## Methodology

To compare the mystery text to the language texts, the tool calculates the frequency distribution of the tokens (individual words or n-grams) in the mystery text. It then compares the frequency distribution to the frequency distributions of the tokens in the Albanian and English language texts. The language that the mystery text is most similar to is determined based on the comparison results.

## Results

If the mystery text is more similar to the Albanian language, it is appended to a file called `updating-al.txt` in the `languages/lang-al/` directory. If it is more similar to the English language, it is appended to a file called `updating-en.txt` in the `languages/lang-en/` directory.

## Conclusion

The language identification tool is a useful tool for identifying the language of a given text. It can be used to improve language identification models and to add new language texts to existing datasets.

## Future Work

In the future, the tool could be expanded to support additional languages and to use more advanced techniques for comparing the mystery text to the language texts.
