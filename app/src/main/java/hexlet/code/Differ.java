package hexlet.code;

import formatters.PlainFormatter;
import formatters.StylishFormatter;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String format) {
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

        if (format.equals("plain")) {
            return PlainFormatter.format(diff);
        } else if (format.equals("stylish")) {
            return StylishFormatter.format(diff);
        }
        return StylishFormatter.format(diff);
    }
}
