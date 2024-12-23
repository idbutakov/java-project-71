import hexlet.code.Differ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class NoFormatterTests {
    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json1.json",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml1.yaml"
    })
    public void testGenerateIdenticalFilesStylish(String filePath1,
                                                  String filePath2) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedNoFormatter1.txt"));
        String result = Differ.generate(filePath1, filePath2);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json2.json",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml2.yaml"
    })
    public void testGenerateDifferentFilesStylish(String filePath1,
                                                  String filePath2) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedNoFormatter2.txt"));
        String result = Differ.generate(filePath1, filePath2);
        assertEquals(expected, result);
    }
}
