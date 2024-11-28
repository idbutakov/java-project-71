package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    public String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    public String filepath2;

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
            Map<String, Object> file1Data = Parser.parseFile(filepath1);
            Map<String, Object> file2Data = Parser.parseFile(filepath2);

            Set<Difference> diff = Differ.generate(file1Data, file2Data);

//            String formattedDiff = switch (format) {
//                case "plain" -> PlainFormatter.format(diff);
//                case "json" -> JsonFormatter.format(diff);
//                default -> StylishFormatter.format(diff);
//            };
            String formattedDiff = StylishFormatter.format(diff);

            System.out.println(formattedDiff);

            return 0;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
            return 1;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
