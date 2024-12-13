package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<Map<String, Object>> differences) throws JsonProcessingException {
        differences.removeIf(diff -> "UNCHANGED".equals(diff.get("status")));

        for (Map<String, Object> diff : differences) {
            String status = (String) diff.get("status");
            Object value1 = diff.get("value1");
            Object value2 = diff.get("value2");

            if ("MODIFIED".equals(status)) {
                diff.put("status", "modified");
                diff.put("oldValue", formatValue(value1));
                diff.put("newValue", formatValue(value2));
                diff.remove("value1");
                diff.remove("value2");
            } else if ("ADDED".equals(status)) {
                diff.put("status", "added");
                diff.put("value", formatValue(value2));
                diff.remove("value1");
                diff.remove("value2");
            } else if ("REMOVED".equals(status)) {
                diff.put("status", "removed");
                diff.put("value", formatValue(value1));
                diff.remove("value1");
                diff.remove("value2");
            }
        }

        return serializeToJson(differences);
    }

    private static Object formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Iterable || value instanceof Object[] || value instanceof Map) {
            return "[complex value]";
        }
        return value;
    }

    private static String serializeToJson(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(data);
        json = json.replace("[ ", "[\n");
        json = json.replaceAll("}, \\{", "},\n{");
        json = json.replace("} ]", "}\n]");
        return json;
    }
}
