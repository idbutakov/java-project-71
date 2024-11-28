package hexlet.code;

import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{\n");

        for (String key : allKeys) {
            boolean inFile1 = map1.containsKey(key);
            boolean inFile2 = map2.containsKey(key);
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String value1Str = String.valueOf(value1);
            String value2Str = String.valueOf(value2);

            if (inFile1 && inFile2 && Objects.equals(value1, value2)) {
                result.append("    ").append(key).append(": ").append(value1Str).append("\n");
            } else {
                if (inFile1) {
                    result.append("  - ").append(key).append(": ").append(value1Str).append("\n");
                }
                if (inFile2) {
                    result.append("  + ").append(key).append(": ").append(value2Str).append("\n");
                }
            }
        }

        result.append("}");
        return result.toString();
    }
}
