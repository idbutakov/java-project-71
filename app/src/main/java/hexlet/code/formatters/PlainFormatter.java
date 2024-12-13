package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String status = (String) diff.get("status");
            Object value1 = diff.get("value1");
            Object value2 = diff.get("value2");

            switch (status) {
                case "MODIFIED":
                    result.append("Property '").append(key)
                            .append("' was updated. From ")
                            .append(formatValue(value1))
                            .append(" to ")
                            .append(formatValue(value2))
                            .append("\n");
                    break;
                case "ADDED":
                    result.append("Property '").append(key)
                            .append("' was added with value: ")
                            .append(formatValue(value2))
                            .append("\n");
                    break;
                case "REMOVED":
                    result.append("Property '").append(key)
                            .append("' was removed")
                            .append("\n");
                    break;
                default:
                    break;
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Iterable || value instanceof Object[] || value instanceof Map) {
            return "[complex value]";
        }
        if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        }
        return value.toString();
    }
}
