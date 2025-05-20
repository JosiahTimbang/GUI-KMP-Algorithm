import java.util.*;

public class RedundantWordChecker {
    public static Map<String, Integer> findRedundantWords(String text) {
        String[] words = text.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (word.length() < 2) continue;
            String lower = word.toLowerCase();
            if (!wordCount.containsKey(lower)) {
                int count = KMPMatcher.countOccurrences(text.toLowerCase(), lower);
                if (count > 1) {
                    wordCount.put(word, count);
                }
            }
        }
        return wordCount;
    }
}
