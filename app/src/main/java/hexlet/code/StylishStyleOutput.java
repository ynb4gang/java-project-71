package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StylishStyleOutput {

    public static String comparator(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        StringBuilder result = new StringBuilder();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());

        for (String key : allKeys) {
            compareKeys(key, parseFileOne, parseFileTwo, result);
        }

        return result.toString();
    }

    private static void compareKeys(String key, Map<String, Object> parseFileOne,
                                    Map<String, Object> parseFileTwo, StringBuilder result) {
        if (parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            Object firstMapValue = parseFileOne.get(key);
            Object secondMapValue = parseFileTwo.get(key);
            if (firstMapValue.equals(secondMapValue)) {
                result.append("  ").append(key).append(": ").append(firstMapValue).append("\n");
            } else {
                result.append("- ").append(key).append(": ").append(firstMapValue).append("\n");
                result.append("+ ").append(key).append(": ").append(secondMapValue).append("\n");
            }
        } else {
            handleMissingKey(key, parseFileOne, parseFileTwo, result);
        }
    }

    private static void handleMissingKey(String key, Map<String, Object> parseFileOne,
                                         Map<String, Object> parseFileTwo, StringBuilder result) {
        if (parseFileOne.containsKey(key)) {
            result.append("- ").append(key).append(": ").append(parseFileOne.get(key)).append("\n");
        }
        if (parseFileTwo.containsKey(key)) {
            result.append("+ ").append(key).append(": ").append(parseFileTwo.get(key)).append("\n");
        }
    }
}
