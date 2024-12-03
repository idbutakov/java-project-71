package formatters;

import hexlet.code.Difference;
import java.util.Set;

public class PlainFormatter {
    public static String format(Set<Difference> differences) {
        StringBuilder result = new StringBuilder();

        for (Difference diff : differences) {
            String key = diff.getKey();
            Difference.Status status = diff.getStatus();
            Object value1 = diff.getValue1();
            Object value2 = diff.getValue2();

            switch (status) {
                case MODIFIED:
                    result.append("Property '").append(key)
                            .append("' was updated. From ")
                            .append(formatValue(value1))
                            .append(" to ")
                            .append(formatValue(value2))
                            .append("\n");
                    break;
                case ADDED:
                    result.append("Property '").append(key)
                            .append("' was added with value: ")
                            .append(formatValue(value2))
                            .append("\n");
                    break;
                case REMOVED:
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
        if (value instanceof Iterable || value instanceof Object[] || value instanceof java.util.Map) {
            return "[complex value]";
        }
        if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        }
        return value.toString();
    }
}
