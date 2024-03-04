package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
class FileUtils {
    public static StringBuilder readFileToStringBuilder(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
        return stringBuilder;
    }
}
class TestJsonStylish {
    @Test
    public void testJsonStylish() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/fixtures/file1.json");
        File fileSecond = new File("src/test/fixtures/file2.json");
        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "stylish");
        assertEquals(expectedOutput, outputJsonStylish);
    }
}
class TestYamlStylish {
    @Test
    public void testYamlStylish() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/fixtures/file1.yml");
        File fileSecond = new File("src/test/fixtures/file2.yml");

        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "stylish");

        assertEquals(outputJsonStylish, expectedOutput);
    }
}

class TestJsonOutputJson {
    @Test
    public void testJsonOutputJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/fixtures/JsonCorrectOutput.json");
        JsonNode expectedOutput = objectMapper.readTree(file);

        File fileFirst = new File("src/test/fixtures/file1.json");
        File fileSecond = new File("src/test/fixtures/file2.json");

        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "json");

        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish);
    }
}

class TestJsonOutputYaml {
    @Test
    public void testJsonOutputYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/fixtures/JsonCorrectOutput.json");
        JsonNode expectedOutput = objectMapper.readTree(file);

        File fileFirst = new File("src/test/fixtures/file1.yml");
        File fileSecond = new File("src/test/fixtures/file2.yml");

        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "json");

        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish);
    }
}

class TestJsonPlain {
    @Test
    public void testJsonPlain() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/PlainCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/fixtures/file1.json");
        File fileSecond = new File("src/test/fixtures/file2.json");

        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "plain");

        assertEquals(expectedOutput, outputJsonStylish);
    }
}
class TestYamlPlain {
    @Test
    public void testYamlPlain() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/PlainCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/fixtures/file1.yml");
        File fileSecond = new File("src/test/fixtures/file2.yml");

        String outputJsonStylish = Differ.generateTest(fileFirst, fileSecond, "plain");
        assertEquals(outputJsonStylish, expectedOutput);
    }
}

class TestDifferJson {
    @Test
    public void testDifferGenerateWithDefaultFormatterJson() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();
        String file1 = "src/test/fixtures/file1.json";
        String file2 = "src/test/fixtures/file2.json";

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }
}
class TestDifferYaml {
    @Test
    public void testDifferGenerateWithAnotherFormatterYaml() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();
        String file1 = "src/test/fixtures/file1.yml";
        String file2 = "src/test/fixtures/file2.yml";

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }
}
