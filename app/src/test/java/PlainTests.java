import hexlet.code.Differ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTests {
    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json1.json, plain",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml1.yaml, plain"
    })
    public void testGenerateIdenticalFilesPlain(String filePath1,
                                                String filePath2,
                                                String format) throws IOException {
        String expected = "";
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json2.json, plain",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml2.yaml, plain"
    })
    public void testGenerateDifferentFilesPlain(String filePath1,
                                                String filePath2,
                                                String format) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedPlain.txt"));
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }
}
