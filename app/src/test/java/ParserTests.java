import hexlet.code.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.util.Map;

class ParserTests {

    @Test
    void testParseFileValidJsonFile() throws IOException {
        int timeout = 50;
        String filePath = "src/test/resources/json1.json";
        Map<String, Object> result = Parser.parseFile(filePath);

        assertNotNull(result);
        assertTrue(result.containsKey("host"));
        assertTrue(result.containsKey("timeout"));
        assertTrue(result.containsKey("proxy"));
        assertTrue(result.containsKey("follow"));
        assertEquals("hexlet.io", result.get("host"));
        assertEquals(timeout, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testParseFileValidYamlFile() throws IOException {
        int timeout = 50;
        String filePath = "src/test/resources/yaml1.yaml";
        Map<String, Object> result = Parser.parseFile(filePath);

        assertNotNull(result);
        assertTrue(result.containsKey("host"));
        assertTrue(result.containsKey("timeout"));
        assertTrue(result.containsKey("proxy"));
        assertTrue(result.containsKey("follow"));
        assertEquals("hexlet.io", result.get("host"));
        assertEquals(timeout, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testParseFileValidYmlFile() throws IOException {
        int timeout = 20;
        String filePath = "src/test/resources/yml1.yml";
        Map<String, Object> result = Parser.parseFile(filePath);

        assertNotNull(result);
        assertTrue(result.containsKey("timeout"));
        assertTrue(result.containsKey("verbose"));
        assertTrue(result.containsKey("host"));
        assertEquals(timeout, result.get("timeout"));
        assertEquals(true, result.get("verbose"));
        assertEquals("hexlet.io", result.get("host"));
    }

    @Test
    void testParseFileFileDoesNotExist() {
        String invalidPath = "src/test/resources/file100.json";

        IOException thrown = assertThrows(IOException.class, () -> Parser.parseFile(invalidPath));
        assertEquals("Файл " + invalidPath + " не существует.", thrown.getMessage());
    }

    @Test
    void testParseFileWrongFileFormat() {
        String wrongFilePath = "src/test/resources/txt1.txt";

        IOException thrown = assertThrows(IOException.class, () -> Parser.parseFile(wrongFilePath));
        assertEquals("Формат файла не поддерживается: " + wrongFilePath, thrown.getMessage());
    }

    @Test
    void testParseFileInvalidJson() {
        String filePath = "src/test/resources/invalidJson.json";

        assertThrows(IOException.class, () -> Parser.parseFile(filePath));
    }
}
