import hexlet.code.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

class JsonParserTests {

    @Test
    void testParseJsonFile_ValidFile() throws IOException {
        String filePath = "src/test/resources/file1.json";
        Map<String, Object> result = JsonParser.parseJsonFile(filePath);

        assertNotNull(result);
        assertTrue(result.containsKey("host"));
        assertTrue(result.containsKey("timeout"));
        assertTrue(result.containsKey("proxy"));
        assertTrue(result.containsKey("follow"));
        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testParseJsonFile_FileDoesNotExist() {
        String invalidPath = "src/test/resources/file100.json";

        IOException thrown = assertThrows(IOException.class, () -> {
            JsonParser.parseJsonFile(invalidPath);
        });
        assertEquals("Файл src/test/resources/file100.json не существует.", thrown.getMessage());
    }

    @Test
    void testParseJsonFile_InvalidJson() {
        String filePath = "src/test/resources/invalidFile.json";

        assertThrows(IOException.class, () -> JsonParser.parseJsonFile(filePath));
    }
}