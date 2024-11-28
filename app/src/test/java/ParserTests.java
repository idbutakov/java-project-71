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
    void testParseJsonFileValidFile() throws IOException {
        String filePath = "src/test/resources/file1.json";
        Map<String, Object> result = Parser.parseFile(filePath);

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
    void testParseJsonFileFileDoesNotExist() {
        String invalidPath = "src/test/resources/file100.json";

        IOException thrown = assertThrows(IOException.class, () -> Parser.parseFile(invalidPath));
        assertEquals("Файл src/test/resources/file100.json не существует.", thrown.getMessage());
    }

    @Test
    void testParseJsonFileInvalidJson() {
        String filePath = "src/test/resources/invalidJson.json";

        assertThrows(IOException.class, () -> Parser.parseFile(filePath));
    }
}
