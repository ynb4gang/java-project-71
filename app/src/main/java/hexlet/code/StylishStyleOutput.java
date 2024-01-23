package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StylishStyleOutput {

    public static Map<String, Object> comparator(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        Map<String, Object> diffParse = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());

        for (String key : allKeys) {
            compareKeys(key, parseFileOne, parseFileTwo, diffParse);
        }

        return diffParse;
    }

    private static void compareKeys(String key, Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo, Map<String, Object> diffParse) {
        if (parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            Object firstMapValue = parseFileOne.get(key);
            Object secondMapValue = parseFileTwo.get(key);
            if (firstMapValue.equals(secondMapValue)) {
                diffParse.put("  " + key, firstMapValue);
            } else {
                diffParse.put("- " + key, firstMapValue);
                diffParse.put("+ " + key, secondMapValue);
            }
        } else {
            handleMissingKey(key, parseFileOne, parseFileTwo, diffParse);
        }
    }

    private static void handleMissingKey(String key, Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo, Map<String, Object> diffParse) {
        if (parseFileOne.containsKey(key)) {
            diffParse.put("- " + key, parseFileOne.get(key));
        }
        if (parseFileTwo.containsKey(key)) {
            diffParse.put("+ " + key, parseFileTwo.get(key));
        }
    }
}
