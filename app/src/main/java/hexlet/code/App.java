package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable{
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
    public void run() {
        System.out.printf("Comparing files '%s' and '%s' with format '%s'%n", filepath1, filepath2, format);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
