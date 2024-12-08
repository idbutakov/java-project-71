import hexlet.code.Differ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFormatterTests {
    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json1.json, json",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml1.yaml, json"
    })
    public void testGenerateIdenticalFilesJsonFormatter(String filePath1,
                                                        String filePath2,
                                                        String format) throws IOException {
        String expected = "[\n]";
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json2.json, json",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml2.yaml, json"
    })
    public void testGenerateDifferentFilesJsonFormatter(String filePath1,
                                                        String filePath2,
                                                        String format) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedJsonFormatter.txt"));
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }
}
