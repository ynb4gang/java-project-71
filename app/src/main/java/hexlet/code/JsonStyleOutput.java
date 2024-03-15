package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static hexlet.code.BuildResult.buildJsonResult;

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
}
