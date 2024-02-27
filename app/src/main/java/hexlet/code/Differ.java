package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.util.Map;

public class Differ {
    public static String generateTest(File filePath1, File filePath2, String format) {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filePath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filePath2);
        switch (format) {
            case "stylish" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
            }
            case "plain" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                return PlainStyleOutput.format(parsedFirstFile, parsedSecondFile);
            }
            case "json" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                JsonNode jsonNode = JsonStyleOutput.format(parsedFirstFile, parsedSecondFile);
                return jsonNode.toPrettyString();
            }
            default -> {
                return "Unknown format";
            }
        }
    }
    public static String generate(String filePath1, String filePath2, String format) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        if (!file1.exists() || !file2.exists()) {
            return "One or both files do not exist.";
        }

        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(file1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(file2);

        switch (format) {
            case "stylish" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                StringBuilder result = new StringBuilder(StylishStyleOutput
                        .comparator(parsedFirstFile, parsedSecondFile));
                if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
                    result.deleteCharAt(result.length() - 1);
                }
                return result.toString();

            }
            case "plain" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                return PlainStyleOutput.format(parsedFirstFile, parsedSecondFile);
            }
            case "json" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                JsonNode jsonNode = JsonStyleOutput.format(parsedFirstFile, parsedSecondFile);
                return jsonNode.toPrettyString();
            }
            default -> {
                return "Unknown format";
            }
        }
    }
    public static String generate(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(file1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(file2);
        assert parsedFirstFile != null;
        assert parsedSecondFile != null;

        return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
    }
}
