package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.BuildResult.buildResultPlain;

public class PlainStyleOutput {
    public static String format(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        StringBuilder result = new StringBuilder();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());
        for (String key : allKeys) {
            buildResultPlain(key, parseFileOne, parseFileTwo, result);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
