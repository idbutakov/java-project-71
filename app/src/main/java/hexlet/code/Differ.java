package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.parseFile(filepath1);
        Map<String, Object> map2 = Parser.parseFile(filepath2);

        List<Map<String, Object>> diff = DiffGenerator.generateDifferences(map1, map2);

        return formatDifferences(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String formatDifferences(List<Map<String, Object>> diff,
                                            String format) throws JsonProcessingException {
        return switch (format) {
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> StylishFormatter.format(diff);
        };
    }
}
