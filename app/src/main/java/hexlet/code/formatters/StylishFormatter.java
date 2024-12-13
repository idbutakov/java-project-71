package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String status = (String) diff.get("status");
            Object value1 = diff.get("value1");
            Object value2 = diff.get("value2");

            switch (status) {
                case "UNCHANGED":
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                    break;
                case "MODIFIED":
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case "ADDED":
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case "REMOVED":
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    break;
                default:
                    break;
            }
        }

        result.append("}");
        return result.toString();
    }
}
