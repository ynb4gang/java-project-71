package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class DifferenceFormatter {
    public static String format(Map<String, Object> parsedFirstFile,
                                Map<String, Object> parsedSecondFile, String format) {
        switch (format) {
            case "stylish" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                StringBuilder result = new StringBuilder(BuildResult
                        .formatStylish(parsedFirstFile, parsedSecondFile));
                if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
                    result.deleteCharAt(result.length() - 1);
                }
                return result.toString();
            }
            case "plain" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                return BuildResult.formatPlain(parsedFirstFile, parsedSecondFile);
            }
            case "json" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                JsonNode jsonNode = BuildResult.formatJson(parsedFirstFile, parsedSecondFile);
                return jsonNode.toPrettyString();
            }
            default -> {
                return "Unknown format";
            }
        }
    }
}

