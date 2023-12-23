package hexlet.code;

import java.util.*;

public class JsonComparator {
    public static Map<String, Object> Comparator(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        TreeSet<String> sortedParam = new TreeSet<>();
        sortedParam.addAll(parseFileOne.keySet());
        sortedParam.addAll(parseFileTwo.keySet());

        Map<String, Object> DiffParse = new LinkedHashMap<>();

        for (String s : sortedParam) {
            if (parseFileOne.containsKey(s) && parseFileTwo.containsKey(s)) {
                Object firstMapValue = parseFileOne.get(s);
                Object secondMapValue = parseFileTwo.get(s);
                if (firstMapValue.equals(secondMapValue)) {
                    DiffParse.put("  " + s, firstMapValue);
                } else {
                    DiffParse.put("- " + s, firstMapValue);
                    DiffParse.put("+ " + s, secondMapValue);
                }
            } else {
                if (parseFileOne.containsKey(s) && !parseFileTwo.containsKey(s)) {
                    DiffParse.put("- " + s, parseFileOne.get(s));
                }
                if (!parseFileOne.containsKey(s) && parseFileTwo.containsKey(s)) {
                    DiffParse.put("+ " + s, parseFileTwo.get(s));
                }
            }
        }
        return DiffParse;
    }
}
