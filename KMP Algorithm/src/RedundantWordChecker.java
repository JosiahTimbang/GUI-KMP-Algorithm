import java.util.*;

public class RedundantWordChecker {
    public static Map<String, Integer> findRedundantWords(String text) {
        // Split text into words, preserving punctuation
        String[] words = text.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Process each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            // Skip words shorter than 2 characters
            if (word.length() < 2) continue;
            
            // Only count if this word hasn't been processed yet
            if (!wordCount.containsKey(word)) {
                int count = 0;
                // Compare with all other words
                for (int j = 0; j < words.length; j++) {
                    if (words[j].toLowerCase().equals(word)) {
                        count++;
                    }
                }
                // Only add to results if word appears more than once
                if (count > 1) {
                    wordCount.put(words[i], count); // Use original word case for display
                }
            }
        }
        return wordCount;
    }
}
