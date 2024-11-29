import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Difference;
import formatters.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {

    @Test
    public void testGenerateIdenticalMaps() throws Exception {
        int[] array = {1, 2, 3, 4};
        String json = "{ \"obj1\": { \"nestedKey\": \"value\", \"isNested\": true } }";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, Object>> map = objectMapper.readValue(json, new TypeReference<>() { });
        Map<String, Object> nestedObj = map.get("obj1");

        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array),
                "key5", nestedObj
        );
        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array),
                "key5", nestedObj
        );

        String expected = """
                {
                    key1: value1
                    key2: 2
                    key3: true
                    key4: [1, 2, 3, 4]
                    key5: {nestedKey=value, isNested=true}
                }""";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateDifferentMaps() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {2, 3, 4, 5};

        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", Arrays.toString(array1)
        );

        Map<String, Object> map2 = Map.of(
                "key1", 3,
                "key2", "new value",
                "key3", Arrays.toString(array2)
        );

        String expected = """
                {
                  - key1: value1
                  + key1: 3
                  - key2: 2
                  + key2: new value
                  - key3: [1, 2, 3, 4]
                  + key3: [2, 3, 4, 5]
                }""";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateOneEmptyMap() {
        int[] array = {1, 2, 3, 4};

        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", Arrays.toString(array)
        );

        Map<String, Object> map2 = Map.of();

        String expected = """
                {
                  - key1: value1
                  - key2: 2
                  - key3: [1, 2, 3, 4]
                }""";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateBothEmptyMaps() {
        Map<String, Object> map1 = Map.of();
        Map<String, Object> map2 = Map.of();

        String expected = "{\n}";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateSameKeyDifferentValues() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {2, 3, 4, 5};

        Map<String, Object> map1 = Map.of(
                "key1", Arrays.toString(array1)
        );
        Map<String, Object> map2 = Map.of(
                "key1", Arrays.toString(array2)
        );

        String expected = """
                {
                  - key1: [1, 2, 3, 4]
                  + key1: [2, 3, 4, 5]
                }""";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }
}
