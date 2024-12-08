import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFormatterTests {
    @Test
    public void testGenerateIdenticalJsonFilesJsonFormatter() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json1.json";

        String expected = "[\n]";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateIdenticalYamlFilesJsonFormatter() throws IOException {
        String filePath1 = "src/test/resources/yaml1.yaml";
        String filePath2 = "src/test/resources/yaml1.yaml";

        String expected = "[\n]";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentJsonFilesJsonFormatter() throws IOException {
        String filePath1 = "src/test/resources/json1.json";
        String filePath2 = "src/test/resources/json2.json";

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "updated",
                  "oldValue" : "value1",
                  "newValue" : 3
                },
                {
                  "property" : "key2",
                  "status" : "updated",
                  "oldValue" : 2,
                  "newValue" : "value2"
                },
                {
                  "property" : "key3",
                  "status" : "updated",
                  "oldValue" : true,
                  "newValue" : "[complex value]"
                },
                {
                  "property" : "key4",
                  "status" : "removed",
                  "value" : "[complex value]"
                },
                {
                  "property" : "key5",
                  "status" : "removed",
                  "value" : "[complex value]"
                }
                ]""";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentYamlFilesJsonFormatter() throws IOException {
        String filePath1 = "src/test/resources/yaml1.yaml";
        String filePath2 = "src/test/resources/yaml2.yaml";

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "updated",
                  "oldValue" : "value1",
                  "newValue" : 3
                },
                {
                  "property" : "key2",
                  "status" : "updated",
                  "oldValue" : 2,
                  "newValue" : "value2"
                },
                {
                  "property" : "key3",
                  "status" : "updated",
                  "oldValue" : true,
                  "newValue" : "[complex value]"
                },
                {
                  "property" : "key4",
                  "status" : "removed",
                  "value" : "[complex value]"
                },
                {
                  "property" : "key5",
                  "status" : "removed",
                  "value" : "[complex value]"
                }
                ]""";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }
}
