package formatters;

import hexlet.code.Difference;

import java.util.Set;

public class StylishFormatter {
    public static String format(Set<Difference> differences) {
        StringBuilder result = new StringBuilder("{\n");

        for (Difference diff : differences) {
            String key = diff.getKey();
            Difference.Status status = diff.getStatus();
            Object value1 = diff.getValue1();
            Object value2 = diff.getValue2();

            switch (status) {
                case UNCHANGED:
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                    break;
                case MODIFIED:
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case ADDED:
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                case REMOVED:
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
