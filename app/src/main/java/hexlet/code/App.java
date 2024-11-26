package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    private String format;

    @Override
    public Integer call() {
        try {
            Map<String, Object> file1Data = JsonParser.parseJsonFile(filepath1);
            Map<String, Object> file2Data = JsonParser.parseJsonFile(filepath2);

            String diff = Differ.generate(file1Data, file2Data);
            System.out.println(diff);

            return 0;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            return 2;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
