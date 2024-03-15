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
    @Test
    public void testJsonStylish() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        String fileFirst = "src/test/resources/fixtures/file1.json";
        String fileSecond = "src/test/resources/fixtures/file2.json";
        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "stylish");
        assertEquals(expectedOutput, outputJsonStylish);
    }
    @Test
    public void testYamlStylish() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        String fileFirst = "src/test/resources/fixtures/file1.yml";
        String fileSecond = "src/test/resources/fixtures/file2.yml";

        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "stylish");

        assertEquals(outputJsonStylish, expectedOutput);
    }
    @Test
    public void testJsonOutputJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/fixtures/JsonCorrectOutput.json");
        JsonNode expectedOutput = objectMapper.readTree(file);

        String fileFirst = "src/test/resources/fixtures/file1.json";
        String fileSecond = "src/test/resources/fixtures/file2.json";

        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "json");

        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish);
    }
    @Test
    public void testJsonOutputYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/fixtures/JsonCorrectOutput.json");
        JsonNode expectedOutput = objectMapper.readTree(file);

        String fileFirst = "src/test/resources/fixtures/file1.yml";
        String fileSecond = "src/test/resources/fixtures/file2.yml";

        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "json");

        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish);
    }
    @Test
    public void testJsonPlain() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/PlainCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        String fileFirst = "src/test/resources/fixtures/file1.json";
        String fileSecond = "src/test/resources/fixtures/file2.json";

        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "plain");

        assertEquals(expectedOutput, outputJsonStylish);
    }
    @Test
    public void testYamlPlain() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/PlainCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();

        String fileFirst = "src/test/resources/fixtures/file1.yml";
        String fileSecond = "src/test/resources/fixtures/file2.yml";

        String outputJsonStylish = Differ.generate(fileFirst, fileSecond, "plain");
        assertEquals(outputJsonStylish, expectedOutput);
    }
    @Test
    public void testDifferGenerateWithDefaultFormatterJson() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();
        String file1 = "src/test/resources/fixtures/file1.json";
        String file2 = "src/test/resources/fixtures/file2.json";

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }
    @Test
    public void testDifferGenerateWithAnotherFormatterYaml() {
        StringBuilder expectedOutputBuilder = FileUtils
                .readFileToStringBuilder("src/test/resources/fixtures/StylishCorrectOutput.txt");

        expectedOutputBuilder.deleteCharAt(expectedOutputBuilder.length() - 1);
        String expectedOutput = expectedOutputBuilder.toString();
        String file1 = "src/test/resources/fixtures/file1.yml";
        String file2 = "src/test/resources/fixtures/file2.yml";

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }
}

