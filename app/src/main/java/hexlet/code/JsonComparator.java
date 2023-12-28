package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class JsonComparator {
    public static Map<String, Object> comparator(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        TreeSet<String> sortedParam = new TreeSet<>();
        sortedParam.addAll(parseFileOne.keySet());
        sortedParam.addAll(parseFileTwo.keySet());

        Map<String, Object> diffParse = new LinkedHashMap<>();

        for (String s : sortedParam) {
            if (parseFileOne.containsKey(s) && parseFileTwo.containsKey(s)) {
                Object firstMapValue = parseFileOne.get(s);
                Object secondMapValue = parseFileTwo.get(s);
                if (firstMapValue.equals(secondMapValue)) {
                    diffParse.put("  " + s, firstMapValue);
                } else {
                    diffParse.put("- " + s, firstMapValue);
                    diffParse.put("+ " + s, secondMapValue);
                }
            } else {
                if (parseFileOne.containsKey(s) && !parseFileTwo.containsKey(s)) {
                    diffParse.put("- " + s, parseFileOne.get(s));
                }
                if (!parseFileOne.containsKey(s) && parseFileTwo.containsKey(s)) {
                    diffParse.put("+ " + s, parseFileTwo.get(s));
                }
            }
        }
        return diffParse;
    }
}
