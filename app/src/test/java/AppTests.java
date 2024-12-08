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
    private static final String FILE6_PATH = "src/test/resources/json4.json";

    @BeforeEach
    void setUp() {
        app = new App();
        app.setFilepath1(FILE1_PATH);
        app.setFilepath2(FILE2_PATH);
        app.setFormat("stylish");
    }

    @Test
    void testCallSuccess() {
        int exitCode = app.call();

        assertEquals(0, exitCode);
    }

    @Test
    void testCallFileNotFound() throws IOException {
        Path file6 = Paths.get(FILE6_PATH);
        Files.deleteIfExists(file6);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        app.setFilepath2(FILE6_PATH);
        int exitCode = app.call();

        String expectedErrorMessage = "Ошибка при чтении файлов: Файл " + FILE6_PATH + " не существует.";
        assertTrue(errContent.toString().contains(expectedErrorMessage));
        assertEquals(1, exitCode);
        Files.createFile(file6);
    }
}
