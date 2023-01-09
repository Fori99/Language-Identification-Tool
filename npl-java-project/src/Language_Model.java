import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Language_Model {
    public static String read_file(String path) {
        try {
            // Create a BufferedReader to read the file
            BufferedReader reader = new BufferedReader(new FileReader(path));

            // Read in each line of the file and collect them into a single string
            String text = reader.lines().collect(Collectors.joining());

            // Close the BufferedReader and return the text
            reader.close();
            return text;
        } catch (IOException e) {
            // If an IOException occurs, print the stack trace and return null
            e.printStackTrace();
            return null;
        }
    }

    public static String[] tokenize_text(String text) {
        try {
            // Lowercase the text
            text = text.toLowerCase();

            // Remove punctuation
            text = text.replaceAll("[^a-zA-Z\\s]", "");

            // Tokenize the text
            return text.split("\\s+");

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, Integer> find_frequency_distribution_for_tokens(String[] tokens, int n) {
        HashMap<String, Integer> ngramFreqDist = new HashMap<>();

        // If n is 1, count the words in the array
        if (n == 1) {
            Arrays.stream(tokens).forEach(token -> ngramFreqDist.merge(token, 1, Integer::sum));
        }
        // If n is greater than 1, count the letters of each word
        else {
            Arrays.stream(tokens).forEach(token -> {
                for (int i = 0; i < token.length() - n + 1; i++) {
                    // Extract the n-gram
                    String ngram = token.substring(i, i + n);
                    // Increment the frequency of the n-gram
                    ngramFreqDist.merge(ngram, 1, Integer::sum);
                }
            });
        }
        return ngramFreqDist;
    }
}
