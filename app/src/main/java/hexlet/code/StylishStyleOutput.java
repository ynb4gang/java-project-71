package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static hexlet.code.BuildResult.compareKeys;

public class StylishStyleOutput {

    public static String comparator(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        StringBuilder result = new StringBuilder();
        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());
        result.append("{\n");
        for (String key : allKeys) {
            compareKeys(key, parseFileOne, parseFileTwo, result);
        }
        result.append("}");
        return result.toString();
    }
}
