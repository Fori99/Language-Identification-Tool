import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Similarity_Check extends Thread {
    // The similarity score between the mystery text and the language being compared
    public Double similarity_check;

    // Path to the folder containing the language texts, the frequency distribution of the mystery text, the name of the language, and the value of n for the n-grams
    private final String folder_path;
    private final HashMap<String, Integer> mystery_hashMap;
    private final Integer n_gram;

    // Constructor for the Similarity_Check class
    public Similarity_Check(String folder_path, HashMap<String, Integer> mystery_hashMap, Integer n_gram) {
        this.folder_path = folder_path;
        this.mystery_hashMap = mystery_hashMap;
        this.n_gram = n_gram;
    }

    @Override
    public void run() {
        try {
            // Read in all the text files in the specified folder
            File source_folder = new File(folder_path);
            String[] thread_text = {""};
            Arrays.stream(Objects.requireNonNull(source_folder.listFiles()))
                    .filter(source_file -> source_file.getName().endsWith(".txt"))
                    .forEach(source_file -> {
                        String file_name = source_file.getName();
                        thread_text[0] = thread_text[0] + " " + Language_Model.read_file(source_folder + "/" + file_name);
                    });

            // Tokenize the text and calculate the frequency distribution of the n-grams
            String[] thread_text_tokens = Language_Model.tokenize_text(thread_text[0]);
            HashMap<String, Integer> thread_hashMap = Language_Model.find_frequency_distribution_for_tokens(thread_text_tokens, n_gram);

            // Calculate the dot product of the two frequency distributions
            int product_of_2_hashMaps = find_product_of_2_hashMaps(mystery_hashMap, thread_hashMap);

            // Calculate the magnitude of the first input HashMap
            double aboslute_value_of_mystery_text = find_absolute_value_of_hashmap(mystery_hashMap);

            // Calculate the magnitude of the second input HashMap
            double aboslute_value_of_thread_text = find_absolute_value_of_hashmap(thread_hashMap);

            // Calculate the Cosine Similarity between the two HashMaps using the dot product and magnitudes
            similarity_check = product_of_2_hashMaps / (aboslute_value_of_mystery_text * aboslute_value_of_thread_text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int find_product_of_2_hashMaps(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        return map1.keySet().stream()
                .filter(map2::containsKey)  // only consider common keys
                .mapToInt(key -> map1.get(key) * map2.get(key))  // multiply values for common keys
                .sum();  // sum the results
    }

    public static double find_absolute_value_of_hashmap(HashMap<String, Integer> map) {
        return Math.sqrt(map.values().stream()
                .mapToInt(value -> value * value)  // square the values
                .sum());  // sum the squared values and take the square root
    }
}
