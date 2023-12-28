package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Map;

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

class JsonComparatorTest {

    @Test
    void testComparator() {

        Map<String, Object> map1 = Map.of("key1", "value1", "key2", 42);
        Map<String, Object> map2 = Map.of("key1", "value2", "key3", true);


        Map<String, Object> result = JsonComparator.Comparator(map1, map2);


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


class ParseJsonTest {

    @Test
    void testParseFile() {

        File file = new File("src/test/resources/testParseFile.json");

        Map<String, Object> result = ParseJson.parseFile(file);

        assertNotNull(result);
        assertEquals("value1", result.get("key1"));
        assertEquals(42, result.get("key2"));
    }
}
