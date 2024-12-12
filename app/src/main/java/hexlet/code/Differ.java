package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.parseFile(filepath1);
        Map<String, Object> map2 = Parser.parseFile(filepath2);

        Set<Difference> diff = DiffGenerator.generateDifferences(map1, map2);

        return formatDifferences(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String formatDifferences(Set<Difference> diff, String format) {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> StylishFormatter.format(diff);
        };
    }
}
