import hexlet.code.Differ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class StylishTests {
    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json1.json, stylish",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml1.yaml, stylish"
    })
    public void testGenerateIdenticalFilesStylish(String filePath1,
                                                  String filePath2,
                                                  String format) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedStylish1.txt"));
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/json1.json, src/test/resources/json2.json, stylish",
        "src/test/resources/yaml1.yaml, src/test/resources/yaml2.yaml, stylish"
    })
    public void testGenerateDifferentFilesStylish(String filePath1,
                                                  String filePath2,
                                                  String format) throws IOException {
        String expected = Files.readString(Paths.get("src/test/resources/expectedStylish2.txt"));
        String result = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, result);
    }
}
