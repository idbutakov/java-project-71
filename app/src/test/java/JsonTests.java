import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTests {
    @Test
    public void testGenerateIdenticalFilesJson() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/json3.json";

        String expected = "[\n]";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentFilesJson() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/json4.json";

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
    public void testGenerateOneEmptyFileJson() throws IOException {
        String filePath1 = "src/test/resources/json3.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "removed",
                  "value" : "value1"
                },
                {
                  "property" : "key2",
                  "status" : "removed",
                  "value" : 2
                },
                {
                  "property" : "key3",
                  "status" : "removed",
                  "value" : true
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
    public void testGenerateBothEmptyFilesJson() throws IOException {
        String filePath1 = "src/test/resources/emptyJson.json";
        String filePath2 = "src/test/resources/emptyJson.json";

        String expected = "[\n]";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesJson() throws IOException {
        String filePath1 = "src/test/resources/json4.json";
        String filePath2 = "src/test/resources/json5.json";

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "updated",
                  "oldValue" : 3,
                  "newValue" : 4
                },
                {
                  "property" : "key2",
                  "status" : "updated",
                  "oldValue" : "value2",
                  "newValue" : "value3"
                },
                {
                  "property" : "key3",
                  "status" : "updated",
                  "oldValue" : "[complex value]",
                  "newValue" : "[complex value]"
                }
                ]""";

        String result = Differ.generate(filePath1, filePath2, "json");
        assertEquals(expected, result);
    }
}
