package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static void run() {
        String filepath1 = "file1.json";
        String filepath2 = "/src/main/files/file2.json";

        Map<String, Object> file1Data = parseJsonFile(filepath1);
        Map<String, Object> file2Data = parseJsonFile(filepath2);

        System.out.println("Данные из первого файла:");
        for (Map.Entry<String, Object> entry : file1Data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nДанные из второго файла:");
        for (Map.Entry<String, Object> entry : file2Data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static Map<String, Object> parseJsonFile(String filePath) {
        Map<String, Object> dataMap = null;

        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                byte[] jsonData = Files.readAllBytes(path);
                ObjectMapper objectMapper = new ObjectMapper();
                dataMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
            } else {
                System.out.println("Файл " + filePath + " не существует.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }
}