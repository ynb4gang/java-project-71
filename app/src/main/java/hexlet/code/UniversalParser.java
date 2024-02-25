package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class UniversalParser {
    public static Map<String, Object> parseFile(File filePath1) {
        try {
            ObjectMapper objectMapper;
            String fileName = filePath1.toString().toLowerCase();
            if (fileName.endsWith(".json")) {
                objectMapper = new ObjectMapper();
            } else if (fileName.endsWith(".yml")) {
                objectMapper = new ObjectMapper(new YAMLFactory());
            } else {
                throw new IOException("Unexpected format file");
            }
            return objectMapper.readValue(filePath1, new TypeReference<TreeMap<String, Object>>() { });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
