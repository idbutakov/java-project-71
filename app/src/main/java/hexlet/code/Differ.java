package hexlet.code;

import formatters.JsonFormatter;
import formatters.PlainFormatter;
import formatters.StylishFormatter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> map1 = Parser.parseFile(filepath1);
        Map<String, Object> map2 = Parser.parseFile(filepath2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        Set<Difference> differences = new HashSet<>();

        for (String key : allKeys) {
            boolean inFile1 = map1.containsKey(key);
            boolean inFile2 = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (inFile1 && inFile2) {
                if (value1 == null && value2 == null) {
                    differences.add(new Difference(key, Difference.Status.UNCHANGED, value1, value2));
                } else if (value1 != null && value2 != null && !value1.equals(value2)) {
                    differences.add(new Difference(key, Difference.Status.MODIFIED, value1, value2));
                } else if (value1 == null && value2 != null) {
                    differences.add(new Difference(key, Difference.Status.MODIFIED, value1, value2));
                } else if (value1 != null && value2 == null) {
                    differences.add(new Difference(key, Difference.Status.MODIFIED, value1, value2));
                } else {
                    differences.add(new Difference(key, Difference.Status.UNCHANGED, value1, value2));
                }
            } else if (inFile1) {
                differences.add(new Difference(key, Difference.Status.REMOVED, value1, null));
            } else if (inFile2) {
                differences.add(new Difference(key, Difference.Status.ADDED, null, value2));
            }
        }

        var diff = new TreeSet<>(differences);

        return switch (format) {
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> StylishFormatter.format(diff);
        };
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
