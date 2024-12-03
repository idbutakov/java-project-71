package formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Difference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonFormatter {
    public static String format(Set<Difference> differences) {
        List<Map<String, Object>> formattedDifferences = new ArrayList<>();

        for (Difference diff : differences) {
            String key = diff.getKey();
            Difference.Status status = diff.getStatus();
            Object value1 = diff.getValue1();
            Object value2 = diff.getValue2();

            Map<String, Object> formattedDiff = new LinkedHashMap<>();
            formattedDiff.put("property", key);

            switch (status) {
                case MODIFIED:
                    formattedDiff.put("status", "updated");
                    formattedDiff.put("oldValue", formatValue(value1));
                    formattedDiff.put("newValue", formatValue(value2));
                    break;
                case ADDED:
                    formattedDiff.put("status", "added");
                    formattedDiff.put("value", formatValue(value2));
                    break;
                case REMOVED:
                    formattedDiff.put("status", "removed");
                    formattedDiff.put("value", formatValue(value1));
                    break;
                default:
                    continue;
            }
            formattedDifferences.add(formattedDiff);
        }

        return serializeToJson(formattedDifferences);
    }

    private static Object formatValue(Object value) {
        if (value instanceof Iterable || value instanceof Object[] || value instanceof Map) {
            return "[complex value]";
        }
        return value;
    }

    private static String serializeToJson(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = mapper.writeValueAsString(data);
            json = json.replace("[ ", "[\n");
            json = json.replaceAll("}, \\{", "},\n{");
            json = json.replace("} ]", "}\n]");
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing to JSON", e);
        }
    }
}
