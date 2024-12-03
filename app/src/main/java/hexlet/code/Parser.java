package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map<String, Object> parseFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Файл " + filePath + " не существует.");
        }

        if (filePath.endsWith(".json")) {
            return parseJsonFile(path);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return parseYamlFile(path);
        } else {
            throw new IOException("Формат файла не поддерживается: " + filePath);
        }
    }

    private static Map<String, Object> parseJsonFile(Path path) throws IOException {
        byte[] jsonData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, new TypeReference<>() { });
    }

    private static Map<String, Object> parseYamlFile(Path path) throws IOException {
        byte[] yamlData = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(yamlData, new TypeReference<>() { });
    }
}
