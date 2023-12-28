package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class ParseJson {
    public static Map<String, Object> parseFile(File filePath1) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(filePath1, new TypeReference<TreeMap<String, Object>>() { });

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
