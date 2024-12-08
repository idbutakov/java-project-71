import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoFormatterTests {
    @Test
    public void testGenerateDifferentJsonFilesStylish() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json2.json";

        String expected = """
                {
                  - key1: value1
                  + key1: 3
                  - key2: 2
                  + key2: value2
                  - key3: true
                  + key3: [2, 3, 4, 5]
                  - key4: [1, 2, 3, 4]
                  - key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(filePath1, filePath2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentYamlFilesStylish() throws IOException {
        String filePath1 = "src/test/resources/yaml1.yaml";
        String filePath2 = "src/test/resources/yaml2.yaml";

        String expected = """
                {
                  - key1: value1
                  + key1: 3
                  - key2: 2
                  + key2: value2
                  - key3: true
                  + key3: [2, 3, 4, 5]
                  - key4: [1, 2, 3, 4]
                  - key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(filePath1, filePath2);
        assertEquals(expected, result);
    }
}
