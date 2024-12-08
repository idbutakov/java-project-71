import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTests {
    @Test
    public void testGenerateIdenticalFilesStylish() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json1.json";

        String expected = """
                {
                    key1: value1
                    key2: 2
                    key3: true
                    key4: [1, 2, 3, 4]
                    key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentFilesStylish() throws IOException {
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

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateOneEmptyFileStylish() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = """
                {
                  - key1: value1
                  - key2: 2
                  - key3: true
                  - key4: [1, 2, 3, 4]
                  - key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateBothEmptyFilesStylish() throws IOException {
        String filePath1 = "src/test/resources/emptyJson.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = "{\n}";

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesStylish() throws IOException {
        String filePath1 = "src/test/resources/json2.json";
        String filePath2 = "src/test/resources/json3.json";

        String expected = """
                {
                  - key1: 3
                  + key1: 4
                  - key2: value2
                  + key2: value3
                  - key3: [2, 3, 4, 5]
                  + key3: [3, 4, 5, 6]
                }""";

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }
}
