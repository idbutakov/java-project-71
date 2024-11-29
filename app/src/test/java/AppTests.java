import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTests {
    private App app;
    private static final String FILE1_PATH = "src/test/resources/json1.json";
    private static final String FILE2_PATH = "src/test/resources/json2.json";
    private static final String FILE3_PATH = "src/test/resources/emptyJson.json";

    @BeforeEach
    void setUp() {
        app = new App();
        app.filepath1 = FILE1_PATH;
        app.filepath2 = FILE2_PATH;
    }

    @Test
    void testCallSuccess() {
        int exitCode = app.call();

        assertEquals(0, exitCode);
    }

    @Test
    void testCallFileNotFound() throws IOException {
        Path file3 = Paths.get(FILE3_PATH);
        Files.deleteIfExists(file3);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        app.filepath2 = FILE3_PATH;
        int exitCode = app.call();

        String expectedErrorMessage = "Ошибка при чтении файлов: Файл " + FILE3_PATH + " не существует.";
        assertTrue(errContent.toString().contains(expectedErrorMessage));
        assertEquals(1, exitCode);
        Files.createFile(file3);
    }
}
