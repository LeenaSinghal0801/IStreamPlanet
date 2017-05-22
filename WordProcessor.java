import java.util.*;

public class WordProcessor {
    private List<Character> wordSeperators;
    private List<Character> sentenceTerminators;
    private boolean caseSensitive;

    public WordProcessor(List<Character> wordSeperators, List<Character> sentenceTerminators, boolean caseSensitive) {
        this.wordSeperators = wordSeperators;
        this.sentenceTerminators = sentenceTerminators;
        this.caseSensitive = caseSensitive;
    }

    public List<String> getMostFrequentWords(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }

        return getMostFrequentWords(buildWordFrequencyMap(str.trim()));
    }

    public HashMap<String, Integer> buildWordFrequencyMap(String str) {
        HashMap<String, Integer> wordFrequency = new HashMap<>();

        if (str == null || str.isEmpty()) {
            return wordFrequency;
        }

        int start = 0, current = 0;
        for (; current < str.length(); current++) {
            if (wordSeperators.contains(str.charAt(current)) || sentenceTerminators.contains(str.charAt(current))) {
                updateWordFrequency(wordFrequency, str.substring(start, current));
                start = current + 1;
            }
        }

        updateWordFrequency(wordFrequency, str.substring(start, current));
        return wordFrequency;
    }

    private List<String> getMostFrequentWords(HashMap<String, Integer> wordFrequencyMap) {
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
            }
        }

        List<String> mostFrequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue().intValue() == maxFrequency) {
                mostFrequentWords.add(entry.getKey());
            }
        }

        return mostFrequentWords;
    }

    private void updateWordFrequency(HashMap<String, Integer> wordFrequency, String word) {

        if (word == null || word.isEmpty()) {
            return;
        }

        if (!caseSensitive) {
            word = word.toLowerCase();
        }

        if (!wordFrequency.containsKey(word)) {
            wordFrequency.put(word, 0);
        }

        wordFrequency.put(word, wordFrequency.get(word) + 1);
    }
}
