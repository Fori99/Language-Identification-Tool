import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // Set the number of consecutive words to consider in the language model
        int n_gram = 1;

        // Read in the mystery text and tokenize it
        String mystery_text = Language_Model.read_file("languages/mystery.txt");
        String[] mystery_text_tokens = Language_Model.tokenize_text(mystery_text);

        // Calculate the frequency distribution of the tokens in the mystery text
        HashMap<String, Integer> mystery_text_hashMap = Language_Model.find_frequency_distribution_for_tokens(mystery_text_tokens, n_gram);

        // Compare the mystery text to Albanian and English language texts
        Similarity_Check check_al = new Similarity_Check("languages/lang-al/", mystery_text_hashMap, n_gram);
        check_al.start();
        Similarity_Check check_en = new Similarity_Check("languages/lang-en/", mystery_text_hashMap, n_gram);
        check_en.start();

        // Wait for the comparison processes to finish
        try
        {
            check_al.join();
            check_en.join();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        // Determine which language the mystery text is most similar to
        if(check_al.similarity_check > check_en.similarity_check)
        {
            System.out.println("This text is similar with the Albanian language.");
            add_text_to_file("languages/lang-al/updating-al.txt", mystery_text);
        }
        else
        {
            System.out.println("This text is similar with the English language.");
            add_text_to_file("languages/lang-en/updating-en.txt", mystery_text);
        }
    }

    // Method to add the given text to the file at the specified path
    public static void add_text_to_file(String path, String mystery_text)
    {
        // Create a File object for the specified file path
        File file_path = new File(path);

        // Try to add the text to the file
        try
        {
            // Create a PrintWriter to write to the file
            PrintWriter printWriter = new PrintWriter(new FileWriter(file_path, true));

            // Append the mystery text to the file, followed by a newline character
            printWriter.append("\n").append(mystery_text);

            printWriter.close();
        }
        // If an IOException occurs while trying to write to the file
        catch(IOException e)
        {
            // Print an error message
            System.out.println("Could not add text to file!");
        }
    }
}