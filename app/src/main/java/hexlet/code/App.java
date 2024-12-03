package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)

public final class App implements Callable<Integer> {
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

    public void setFilepath1(String filepath1) {
        this.filepath1 = filepath1;
    }

    public void setFilepath2(String filepath2) {
        this.filepath2 = filepath2;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);

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
