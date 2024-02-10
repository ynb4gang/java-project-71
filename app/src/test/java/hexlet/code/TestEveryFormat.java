package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestJsonStylish {
    @Test
    public void testJsonStylish() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/StylishCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/resources/file1.json");
        File fileSecond = new File("src/test/resources/file2.json");
        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);
        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);
        String outputJsonStylish = StylishStyleOutput.comparator(parsedFileFirst, parsedFileSecond);
        assertEquals(expectedOutput, outputJsonStylish);
    }
}
class TestYamlStylish {
    @Test
    public void testYamlStylish() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/StylishCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/resources/file1.yaml");
        File fileSecond = new File("src/test/resources/file2.yaml");

        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);

        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);
        String outputJsonStylish = StylishStyleOutput.comparator(parsedFileFirst, parsedFileSecond);
        assertEquals(outputJsonStylish, expectedOutput);
    }
}

class TestJsonOutputJson {
    @Test
    public void testJsonOutputJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/JsonCorrectOutput.txt");
        JsonNode expectedOutput = objectMapper.readTree(file);

        File fileFirst = new File("src/test/resources/file1.json");
        File fileSecond = new File("src/test/resources/file2.json");

        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);

        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);

        JsonNode outputJsonStylish = JsonStyleOutput.format(parsedFileFirst, parsedFileSecond);
        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish.toPrettyString());
    }
}

class TestJsonOutputYaml {
    @Test
    public void testJsonOutputYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/JsonCorrectOutput.txt");
        JsonNode expectedOutput = objectMapper.readTree(file);

        File fileFirst = new File("src/test/resources/file1.yaml");
        File fileSecond = new File("src/test/resources/file2.yaml");

        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);

        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);

        JsonNode outputJsonStylish = JsonStyleOutput.format(parsedFileFirst, parsedFileSecond);
        assertEquals(expectedOutput.toPrettyString(), outputJsonStylish.toPrettyString());
    }
}

class TestJsonPlain {
    @Test
    public void testJsonPlain() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/PlainCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/resources/file1.json");
        File fileSecond = new File("src/test/resources/file2.json");
        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);
        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);
        String outputJsonStylish = PlainStyleOutput.format(parsedFileFirst, parsedFileSecond);
        assertEquals(expectedOutput, outputJsonStylish);
    }
}
class TestYamlPlain {
    @Test
    public void testYamlPlain() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/PlainCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();

        File fileFirst = new File("src/test/resources/file1.yaml");
        File fileSecond = new File("src/test/resources/file2.yaml");

        Map<String, Object> parsedFileFirst = UniversalParser.parseFile(fileFirst);
        Map<String, Object> parsedFileSecond = UniversalParser.parseFile(fileSecond);

        assertNotNull(parsedFileFirst);
        assertNotNull(parsedFileSecond);
        String outputJsonStylish = PlainStyleOutput.format(parsedFileFirst, parsedFileSecond);
        assertEquals(outputJsonStylish, expectedOutput);
    }
}

class DifferTest {
    @Test
    public void testDifferGenerateWithDefaultFormatterJson() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/StylishCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();
        File file1 = new File("src/test/resources/file1.json");
        File file2 = new File("src/test/resources/file2.json");

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }

    @Test
    public void testDifferGenerateWithAnotherFormatterYaml() {
        StringBuilder expectedOutputBuilder = new StringBuilder();
        File expectedFile = new File("src/test/resources/StylishCorrectOutput.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expectedOutputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String expectedOutput = expectedOutputBuilder.toString();
        File file1 = new File("src/test/resources/file1.yaml");
        File file2 = new File("src/test/resources/file2.yaml");

        String diff = Differ.generate(file1, file2);

        assertEquals(expectedOutput, diff);
    }
}
