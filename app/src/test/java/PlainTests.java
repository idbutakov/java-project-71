import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTests {
    @Test
    public void testGenerateIdenticalFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/json3.json";

        String expected = "";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/json4.json";

        String expected = """
                Property 'key1' was updated. From 'value1' to 3
                Property 'key2' was updated. From 2 to 'value2'
                Property 'key3' was updated. From true to [complex value]
                Property 'key4' was removed
                Property 'key5' was removed""";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateOneEmptyFilePlain() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = """
                Property 'key1' was removed
                Property 'key2' was removed
                Property 'key3' was removed
                Property 'key4' was removed
                Property 'key5' was removed""";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateBothEmptyFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/emptyJson.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = "{\n}";

        String result = Differ.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesPlain() throws IOException {
        String filePath1 = "src/test/resources/json4.json";
        String filePath2 = "src/test/resources/json5.json";

        String expected = """
                Property 'key1' was updated. From 3 to 4
                Property 'key2' was updated. From 'value2' to 'value3'
                Property 'key3' was updated. From [complex value] to [complex value]""";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }
}
