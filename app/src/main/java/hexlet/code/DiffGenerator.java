package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DiffGenerator {
    public static List<Map<String, Object>> generateDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> differences = new ArrayList<>();

        for (String key : allKeys) {
            boolean inFile1 = map1.containsKey(key);
            boolean inFile2 = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            Map<String, Object> diff = new LinkedHashMap<>();
            diff.put("key", key);

            if (inFile1 && inFile2) {
                if (Objects.equals(value1, value2)) {
                    diff.put("status", "UNCHANGED");
                    diff.put("value1", value1);
                    diff.put("value2", value2);
                } else {
                    diff.put("status", "MODIFIED");
                    diff.put("value1", value1);
                    diff.put("value2", value2);
                }
            } else if (inFile1) {
                diff.put("status", "REMOVED");
                diff.put("value1", value1);
                diff.put("value2", null);
            } else if (inFile2) {
                diff.put("status", "ADDED");
                diff.put("value1", null);
                diff.put("value2", value2);
            }

            differences.add(diff);
        }

        return differences;
    }
}
