package hexlet.code;


import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.stream.Collectors;


public class PlainStyleOutput {
    public static String format(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        StringBuilder result = new StringBuilder();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());
        for (String key : allKeys) {
            buildResult(key, parseFileOne, parseFileTwo, result);
        }
        return result.toString();
    }

    private static void buildResult(String key, Map<String, Object> parseFileOne,
                                    Map<String, Object> parseFileTwo, StringBuilder result) {
        Object valueOne = parseFileOne.get(key);
        Object valueTwo = parseFileTwo.get(key);

        if (valueOne == null && valueTwo != null) {
            addPropertyAdded(key, valueTwo, result);
        } else if (valueOne != null && valueTwo == null) {
            addPropertyRemoved(key, result);
        } else if (valueOne != null && valueTwo != null && !Objects.equals(valueOne, valueTwo)) {
            addPropertyUpdated(key, valueOne, valueTwo, result);
        }
    }

    private static void addPropertyAdded(String key, Object value, StringBuilder result) {
        if (value instanceof Map || value instanceof List) {
            result.append("Property '").append(key).append("' was added with value: [complex value]\n");
        } else {
            result.append("Property '").append(key).append("' was added with value: ").append(value).append("\n");
        }
    }

    private static void addPropertyRemoved(String key, StringBuilder result) {
        result.append("Property '").append(key).append("' was removed\n");
    }

    private static void addPropertyUpdated(String key, Object valueOne, Object valueTwo, StringBuilder result) {
        if ((valueOne instanceof Map || valueOne instanceof List)
                && (valueTwo instanceof Map || valueTwo instanceof List)) {
            result.append("Property '").append(key).append("' was updated. From [complex value] to [complex value]\n");
        } else if (valueOne instanceof Map && valueTwo instanceof Map) {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append(mapToString((Map<?, ?>) valueOne)).append(" to ")
                    .append(mapToString((Map<?, ?>) valueTwo)).append("\n");
        } else if (valueOne instanceof List && valueTwo instanceof List) {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append(listToString((List<?>) valueOne)).append(" to ")
                    .append(listToString((List<?>) valueTwo)).append("\n");
        } else {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append(valueOne).append(" to ").append(valueTwo).append("\n");
        }
    }

    private static String mapToString(Map<?, ?> map) {
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private static String listToString(List<?> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}