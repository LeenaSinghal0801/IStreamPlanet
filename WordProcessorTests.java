import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordProcessorTests {
    @Test
    public void singleFrequentWordTest() {
        // basic case
        WordProcessor sp = new WordProcessor(Arrays.asList(' ', '-', ','), Arrays.asList('.', '\n', '!', '?'), false);
        String str = "Hello, I am doing good. How are you doing?.";
        HashMap<String, Integer> map = sp.buildWordFrequencyMap(str);
        assert (map.size() == 8);
        assert (map.get("hello") == 1);
        assert (map.get("i") == 1);
        assert (map.get("am") == 1);
        assert (map.get("doing") == 2);
        assert (map.get("good") == 1);
        assert (map.get("how") == 1);
        assert (map.get("are") == 1);
        assert (map.get("you") == 1);
        List<String> mostFrequentWords = sp.getMostFrequentWords(str);
        assert (mostFrequentWords.size() == 1);
        assert (mostFrequentWords.contains("doing"));
    }

    @Test
    public void multipleFrequentWordTest() {
        WordProcessor sp = new WordProcessor(Arrays.asList(' ', '-', ','), Arrays.asList('.', '\n', '!', '?'), true);
        String str = "Hello hello hello! How is is it??";
        HashMap<String, Integer> map = sp.buildWordFrequencyMap(str);
        assert (map.size() == 5);
        assert (map.get("Hello") == 1);
        assert (map.get("hello") == 2);
        assert (map.get("How") == 1);
        assert (map.get("is") == 2);
        assert (map.get("it") == 1);
        List<String> mostFrequentWords = sp.getMostFrequentWords(str);
        assert (mostFrequentWords.size() == 2);
        assert (mostFrequentWords.contains("hello"));
        assert (mostFrequentWords.contains("is"));
    }

    @Test
    public void emptyStringTests() {
        WordProcessor sp = new WordProcessor(Arrays.asList(' ', '-', ','), Arrays.asList('.', '\n', '!', '?'), true);
        String[] emptyCases = new String[]{null, "", " ", ".", "!!!", "?. "};
        for (int i = 0; i < emptyCases.length; i++) {
            HashMap<String, Integer> map = sp.buildWordFrequencyMap(emptyCases[i]);
            assert (map.size() == 0);
            List<String> mostFrequentWords = sp.getMostFrequentWords(emptyCases[i]);
            assert (mostFrequentWords.size() == 0);
        }
    }
}