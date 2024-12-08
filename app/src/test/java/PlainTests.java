import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTests {
    @Test
    public void testGenerateIdenticalJsonFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json1.json";

        String expected = "";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateIdenticalYamlFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/yaml1.yaml";
        String filePath2 = "src/test/resources/yaml1.yaml";

        String expected = "";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentJsonFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json2.json";

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
    public void testGenerateDifferentYamlFilesPlain() throws IOException {
        String filePath1 = "src/test/resources/yaml1.yaml";
        String filePath2 = "src/test/resources/yaml2.yaml";

        String expected = """
                Property 'key1' was updated. From 'value1' to 3
                Property 'key2' was updated. From 2 to 'value2'
                Property 'key3' was updated. From true to [complex value]
                Property 'key4' was removed
                Property 'key5' was removed""";

        String result = Differ.generate(filePath1, filePath2, "plain");
        assertEquals(expected, result);
    }
}
