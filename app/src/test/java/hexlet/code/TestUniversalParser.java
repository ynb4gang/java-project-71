package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.File;
import java.util.Map;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testCall() throws Exception {

        File file1 = new File("src/test/resources/testFile1.json");
        File file2 = new File("src/test/resources/testFile2.json");


        App app = new App();
        app.filepath1 = file1;
        app.filepath2 = file2;
        int exitCode = app.call();


        assertEquals(0, exitCode);
    }
}

class StylishStyleOutputTest {

    @Test
    void testComparator() {

        Map<String, Object> map1 = Map.of("key1", "value1", "key2", 42);
        Map<String, Object> map2 = Map.of("key1", "value2", "key3", true);


        Map<String, Object> result = StylishStyleOutput.comparator(map1, map2);


        assertTrue(result.containsKey("- key2"));
        assertEquals(42, result.get("- key2"));
        assertNull(result.get("+ key2"));
        assertTrue(result.containsKey("- key1"));
        assertTrue(result.containsKey("+ key1"));
        assertEquals("value1", result.get("- key1"));
        assertEquals("value2", result.get("+ key1"));
        assertFalse(result.containsKey("- key3"));
        assertNull(result.get("- key3"));
    }
}


class UniversalParserTest {
    @Test
    void testParseFile() {

        File file = new File("src/test/resources/testParseFile.json");

        Map<String, Object> result = UniversalParser.parseFile(file);

        assertNotNull(result);
        assertEquals("value1", result.get("key1"));
        assertEquals(42, result.get("key2"));
    }
}

class TestYamlParse {
    @Test
    void testParseYamlFile() {
        File file = new File("src/test/resources/fileTestYaml.yaml");
        Map<String, Object> result = UniversalParser.parseFile(file);
        assertNotNull(result);
        assertEquals("value3", result.get(("key1")));
        assertEquals(666, result.get("key2"));
    }
}

class TestAppYaml {
    @Test
    void testCall() throws Exception {
        File file1 = new File("src/test/resources/fileTest1Yaml.yaml");
        File file2 = new File("src/test/resources/fileTest2Yaml.yaml");

        App app = new App();
        app.filepath1 = file1;
        app.filepath2 = file2;
        int exitCode = app.call();

        assertEquals(0, exitCode);
    }
}

class JsonStyleOutputTest {

    @Test
    void testJsonStyleOutput() {
        File firstFile = new File("src/test/resources/testFile1.json");
        File secondFile = new File("src/test/resources/testFile2.json");
        Map<String, Object> parseFileOne = UniversalParser.parseFile(firstFile);
        Map<String, Object> parseFileTwo = UniversalParser.parseFile(secondFile);

        assert parseFileOne != null;
        assert parseFileTwo != null;
        JsonNode result = JsonStyleOutput.format(parseFileOne, parseFileTwo);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        // follow
        ObjectNode followNode = objectMapper.createObjectNode();
        followNode.put("value", false);
        followNode.put("status", "removed");
        jsonNode.set("follow", followNode);

        // host
        ObjectNode hostNode = objectMapper.createObjectNode();
        hostNode.put("value", "hexlet.io");
        hostNode.put("status", "unchanged");
        jsonNode.set("host", hostNode);

        // proxy
        ObjectNode proxyNode = objectMapper.createObjectNode();
        proxyNode.put("value", "123.234.53.22");
        proxyNode.put("status", "removed");
        jsonNode.set("proxy", proxyNode);

        // timeout
        ObjectNode timeoutNode = objectMapper.createObjectNode();
        timeoutNode.put("oldValue", 50);
        timeoutNode.put("newValue", 20);
        jsonNode.set("timeout", timeoutNode);

        // verbose
        ObjectNode verboseNode = objectMapper.createObjectNode();
        verboseNode.put("value", true);
        verboseNode.put("status", "added");
        jsonNode.set("verbose", verboseNode);

        assertEquals(jsonNode.toString(), result.toString());
    }
}
