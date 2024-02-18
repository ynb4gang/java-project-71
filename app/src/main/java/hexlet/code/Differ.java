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
    public static void generate(File filePath1, File filePath2, String format) {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filePath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filePath2);
        switch (format) {
            case "stylish" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                String readyParseOutput = StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
                System.out.println(readyParseOutput);
            }
            case "plain" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                String result = PlainStyleOutput.format(parsedFirstFile, parsedSecondFile);
                System.out.println(result);
            }
            case "json" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                JsonNode jsonNode = JsonStyleOutput.format(parsedFirstFile, parsedSecondFile);
                System.out.println(jsonNode.toPrettyString());
            }
            default -> {
                System.out.println("Unknown format");
            }
        }
    }
    public static String generate(File filePath1, File filePath2) {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filePath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filePath2);
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
        return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
    }
}
