package hexlet.code;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BuildResult {
    public static void buildJsonResult(String key, Map<String, Object> parseFileOne,
                                       Map<String, Object> parseFileTwo, ObjectNode jsonNode) {
        ObjectNode nestedNode = jsonNode.putObject(key);

        if (parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            if (parseFileOne.get(key) != null && parseFileTwo.get(key) != null) {
                if (parseFileOne.get(key).equals(parseFileTwo.get(key))) {
                    nestedNode.putPOJO("value", parseFileOne.get(key));
                    nestedNode.putPOJO("status", "unchanged");
                } else {
                    nestedNode.putPOJO("oldValue", parseFileOne.get(key));
                    nestedNode.putPOJO("newValue", parseFileTwo.get(key));
                }
            } else {
                nestedNode.putPOJO("oldValue", parseFileOne.get(key));
                nestedNode.putPOJO("newValue", parseFileTwo.get(key));
            }
        } else if (parseFileOne.containsKey(key) && !parseFileTwo.containsKey(key)) {
            nestedNode.putPOJO("value", parseFileOne.get(key));
            nestedNode.put("status", "removed");
        } else if (!parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            nestedNode.putPOJO("value", parseFileTwo.get(key));
            nestedNode.put("status", "added");
        }
    }

    protected static void buildResultPlain(String key, Map<String, Object> parseFileOne,
                                         Map<String, Object> parseFileTwo, StringBuilder result) {
        Object valueOne = parseFileOne.get(key);
        Object valueTwo = parseFileTwo.get(key);

        if (!parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            addPropertyAdded(key, valueTwo, result);
        } else if (parseFileOne.containsKey(key) && !parseFileTwo.containsKey(key)) {
            addPropertyRemoved(key, result);
        } else if (parseFileOne.containsKey(key)
                && parseFileTwo.containsKey(key) && !Objects.equals(valueOne, valueTwo)) {
            addPropertyUpdated(key, valueOne, valueTwo, result);
        }
    }

    private static void addPropertyAdded(String key, Object value, StringBuilder result) {
        if (value instanceof Map || value instanceof List || (value != null && value.getClass().isArray())) {
            result.append("Property '").append(key).append("' was added with value: [complex value]\n");
        } else {
            if (value instanceof String) {
                result.append("Property '").append(key).append("' was added with value: '").append(value).append("'\n");
            } else {
                result.append("Property '").append(key)
                        .append("' was added with value: ").append(value).append("\n");
            }
        }
    }

    private static void addPropertyRemoved(String key, StringBuilder result) {
        result.append("Property '").append(key).append("' was removed\n");
    }

    private static void addPropertyUpdated(String key, Object valueOne, Object valueTwo, StringBuilder result) {
        if ((valueOne instanceof Map || valueOne instanceof List || (valueOne != null && valueOne.getClass().isArray()))
                && (valueTwo instanceof Map || valueTwo instanceof List || valueTwo.getClass().isArray())) {
            result.append("Property '").append(key).append("' was updated. From [complex value] to [complex value]\n");
        } else if (valueOne instanceof Map || valueOne instanceof List
                || (valueOne != null && valueOne.getClass().isArray())) {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append("[complex value]").append(" to ")
                    .append(valueTwo).append("\n");
        } else if (valueTwo instanceof Map || valueTwo instanceof List
                || (valueTwo != null && valueTwo.getClass().isArray())) {
            result.append("Property '").append(key).append("' was updated. From ")
                    .append(valueOne).append(" to ")
                    .append("[complex value]").append("\n");
        } else {
            if (valueOne instanceof String && valueTwo instanceof String) {
                result.append("Property '").append(key).append("' was updated. From '")
                        .append(valueOne).append("' to '").append(valueTwo).append("'\n");
            } else if (valueOne instanceof String && !(valueTwo instanceof  String)) {
                result.append("Property '").append(key).append("' was updated. From '")
                        .append(valueOne).append("' to ").append(valueTwo).append("\n");
            } else if (!(valueOne instanceof String) && valueTwo instanceof  String) {
                result.append("Property '").append(key).append("' was updated. From ")
                        .append(valueOne).append(" to '").append(valueTwo).append("'\n");
            } else {
                result.append("Property '").append(key).append("' was updated. From ")
                        .append(valueOne).append(" to ").append(valueTwo).append("\n");
            }
        }
    }

    protected static void compareKeys(String key, Map<String, Object> parseFileOne,
                                    Map<String, Object> parseFileTwo, StringBuilder result) {
        if (parseFileOne.containsKey(key) && parseFileTwo.containsKey(key)) {
            Object firstMapValue = parseFileOne.get(key);
            Object secondMapValue = parseFileTwo.get(key);
            if (firstMapValue == null && secondMapValue == null) {
                result.append("    ").append(key).append(": ").append("null").append("\n");
            } else if (firstMapValue == null) {
                result.append("  - ").append(key).append(": ").append("null").append("\n");
                result.append("  + ").append(key).append(": ").append(secondMapValue).append("\n");
            } else if (secondMapValue == null) {
                result.append("  - ").append(key).append(": ").append(firstMapValue).append("\n");
                result.append("  + ").append(key).append(": ").append("null").append("\n");
            } else if (firstMapValue.equals(secondMapValue)) {
                result.append("    ").append(key).append(": ").append(firstMapValue).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(firstMapValue).append("\n");
                result.append("  + ").append(key).append(": ").append(secondMapValue).append("\n");
            }
        } else {
            handleMissingKey(key, parseFileOne, parseFileTwo, result);
        }
    }

    private static void handleMissingKey(String key, Map<String, Object> parseFileOne,
                                         Map<String, Object> parseFileTwo, StringBuilder result) {
        if (parseFileOne.containsKey(key)) {
            result.append("  - ").append(key).append(": ").append(parseFileOne.get(key)).append("\n");
        }
        if (parseFileTwo.containsKey(key)) {
            result.append("  + ").append(key).append(": ").append(parseFileTwo.get(key)).append("\n");
        }
    }
}
