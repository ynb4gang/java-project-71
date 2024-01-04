package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class JsonStyleOutput {

    public static JsonNode format(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());

        for (String key : allKeys) {
            buildJsonResult(key, parseFileOne, parseFileTwo, jsonNode);
        }

        return jsonNode;
    }

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
}
