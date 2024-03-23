package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.PlainStyleOutput.buildResultPlain;

public class BuildResult {
    public static String formatStylish(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        StringBuilder result = new StringBuilder();
        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());
        result.append("{\n");
        for (String key : allKeys) {
            StylishStyleOutput.compareKeys(key, parseFileOne, parseFileTwo, result);
        }
        result.append("}");
        return result.toString();
    }
    public static JsonNode formatJson(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        Set<String> allKeys = new TreeSet<>(parseFileOne.keySet());
        allKeys.addAll(parseFileTwo.keySet());

        for (String key : allKeys) {
            JsonStyleOutput.buildJsonResult(key, parseFileOne, parseFileTwo, jsonNode);
        }
        return jsonNode;
    }
    public static String formatPlain(Map<String, Object> parseFileOne, Map<String, Object> parseFileTwo) {
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
