package hexlet.code;

import java.io.File;
import java.util.Map;

public class Differ {
    public static String generate(File filePath1, File filePath2) {
        Map<String, Object> parsedFirstFile = UniversalParser.parseFile(filePath1);
        Map<String, Object> parsedSecondFile = UniversalParser.parseFile(filePath2);
        assert parsedSecondFile != null;
        assert parsedFirstFile != null;
        return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
    }
}