package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static Map<String, Object> parseJsonFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Файл " + filePath + " не существует.");
        }

        byte[] jsonData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, new TypeReference<>() {});
    }
}
