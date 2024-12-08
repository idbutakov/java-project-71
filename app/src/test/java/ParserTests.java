import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTests {
    private static final int KEY_2 = 2;
    private static Map<String, Object> expectedResult;

    @BeforeAll
    static void setUp() {
        ArrayList<Integer> array1 = new ArrayList<>();
        array1.add(1);
        array1.add(2);
        array1.add(3);
        array1.add(4);

        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("nestedKey", "value");
        nestedMap.put("isNested", true);

        expectedResult = new HashMap<>();
        expectedResult.put("key1", "value1");
        expectedResult.put("key2", KEY_2);
        expectedResult.put("key3", true);
        expectedResult.put("key4", array1);
        expectedResult.put("key5", nestedMap);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "src/test/resources/json1.json",
        "src/test/resources/yaml1.yaml",
        "src/test/resources/yml1.yml"
    })
    void testParseValidFile(String filePath) throws IOException {
        Map<String, Object> result = Parser.parseFile(filePath);

        assertNotNull(result);
        assertTrue(result.containsKey("key1"));
        assertTrue(result.containsKey("key2"));
        assertTrue(result.containsKey("key3"));
        assertTrue(result.containsKey("key4"));
        assertTrue(result.containsKey("key5"));
        assertEquals(expectedResult.get("key1"), result.get("key1"));
        assertEquals(expectedResult.get("key2"), result.get("key2"));
        assertEquals(expectedResult.get("key3"), result.get("key3"));
        assertEquals(expectedResult.get("key4"), result.get("key4"));
        assertEquals(expectedResult.get("key5"), result.get("key5"));
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
