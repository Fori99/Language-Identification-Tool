# Language Identification Tool

This project is a tool for identifying the language of a given text. It compares the text to texts in the Albanian and English languages using n-grams and determines which language the text is most similar to.

## Getting Started

To run this project on your local machine, you will need to install the following software:

- Java

Once you have installed Java, you can clone this repository and build and run the project using the following commands:

git clone https://github.com/<your-username>/language-identification-tool.git
cd language-identification-tool
javac Main.java
java Main

To specify the number of consecutive words to consider in the language model (n-grams), set the `n_gram` variable in the `main` method.

To use a different mystery text, replace the contents of the `languages/mystery.txt` file with your desired text.

## Built With

* Java

## Contributing

If you would like to contribute to this project, please follow these guidelines:

1. Fork the repository
2. Create a new branch for your changes (e.g. `feature/new-feature` or `bugfix/fix-bug`)
3. Commit your changes to the new branch
4. Open a pull request from the new branch to the `master` branch

## License
Mozilla Public License 2.0



