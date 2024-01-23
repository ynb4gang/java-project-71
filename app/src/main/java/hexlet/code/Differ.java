package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format ) {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filePath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filePath2);
        switch (format) {
            case "stylish" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                String readyParseOutput = StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
                return  readyParseOutput;
            }
            case "plain" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                String result = PlainStyleOutput.format(parsedFirstFile, parsedSecondFile);
                return result;
            }
            case "json" -> {
                assert parsedFirstFile != null;
                assert parsedSecondFile != null;
                JsonNode jsonNode = JsonStyleOutput.format(parsedFirstFile, parsedSecondFile);
                return jsonNode.toString();
            }
        }
        return "";
    }
    public static String generate(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(file1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(file2);
        assert parsedSecondFile != null;
        assert parsedFirstFile != null;
        return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
    }
}