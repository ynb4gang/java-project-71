package hexlet.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath();
        Path path2 = Paths.get(filePath2).toAbsolutePath();

        if (!Files.exists(path1) || !Files.exists(path2)) {
            return "One or both files do not exist.";
        }

        FileInputStream fileInputStream1 = new FileInputStream(path1.toFile());
        FileInputStream fileInputStream2 = new FileInputStream(path2.toFile());
        Map<String, Object> parsedFirstFile = UniversalParser.parseData(fileInputStream1, getFileExtension(filePath1));
        Map<String, Object> parsedSecondFile = UniversalParser.parseData(fileInputStream2, getFileExtension(filePath2));


        return DifferenceFormatter.format(format, parsedFirstFile, parsedSecondFile);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        Map<String, Object> parsedFirstFile = UniversalParser
                .parseData(new FileInputStream(file1), getFileExtension(filePath1));
        Map<String, Object> parsedSecondFile = UniversalParser
                .parseData(new FileInputStream(file2), getFileExtension(filePath2));
        assert parsedFirstFile != null;
        assert parsedSecondFile != null;

        return StylishStyleOutput.comparator(parsedFirstFile, parsedSecondFile);
    }


    private static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }
}
