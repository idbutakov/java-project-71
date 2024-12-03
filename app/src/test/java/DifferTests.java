import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {
    static int[] array1;
    static int[] array2;
    static Map<String, Object> nestedObj;

    @BeforeAll
    public static void init() throws Exception {
        array1 = new int[]{1, 2, 3, 4};
        array2 = new int[]{2, 3, 4, 5};

        String json = "{ \"obj1\": { \"nestedKey\": \"value\", \"isNested\": true } }";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, Object>> map = objectMapper.readValue(json, new TypeReference<>() { });
        nestedObj = map.get("obj1");
    }

    @Test
    public void testGenerateIdenticalMapsStylish() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
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

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentMapsStylish() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", 3,
                "key2", "value2",
                "key3", Arrays.toString(array2)
        );

        String expected = """
                {
                  - key1: value1
                  + key1: 3
                  - key2: 2
                  + key2: value2
                  - key3: true
                  + key3: [2, 3, 4, 5]
                  - key4: [1, 2, 3, 4]
                  - key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateOneEmptyMapStylish() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of();

        String expected = """
                {
                  - key1: value1
                  - key2: 2
                  - key3: true
                  - key4: [1, 2, 3, 4]
                  - key5: {nestedKey=value, isNested=true}
                }""";

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateBothEmptyMapsStylish() {
        Map<String, Object> map1 = Map.of();
        Map<String, Object> map2 = Map.of();

        String expected = "{\n}";

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesStylish() {
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

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateIdenticalMapsPlain() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        String expected = "";

        String result = Differ.generate(map1, map2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentMapsPlain() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", 3,
                "key2", "value2",
                "key3", Arrays.toString(array2)
        );

        String expected = """
                Property 'key1' was updated. From 'value1' to 3
                Property 'key2' was updated. From 2 to 'value2'
                Property 'key3' was updated. From true to '[2, 3, 4, 5]'
                Property 'key4' was removed
                Property 'key5' was removed""";

        String result = Differ.generate(map1, map2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateOneEmptyMapPlain() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of();

        String expected = """
                Property 'key1' was removed
                Property 'key2' was removed
                Property 'key3' was removed
                Property 'key4' was removed
                Property 'key5' was removed""";

        String result = Differ.generate(map1, map2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateBothEmptyMapsPlain() {
        Map<String, Object> map1 = Map.of();
        Map<String, Object> map2 = Map.of();

        String expected = "{\n}";

        String result = Differ.generate(map1, map2, "stylish");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesPlain() {
        Map<String, Object> map1 = Map.of(
                "key1", Arrays.toString(array1)
        );
        Map<String, Object> map2 = Map.of(
                "key1", Arrays.toString(array2)
        );

        String expected = """
                Property 'key1' was updated. From '[1, 2, 3, 4]' to '[2, 3, 4, 5]'""";

        String result = Differ.generate(map1, map2, "plain");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateIdenticalMapsJson() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        String expected = "[\n]";

        String result = Differ.generate(map1, map2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateDifferentMapsJson() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of(
                "key1", 3,
                "key2", "value2",
                "key3", Arrays.toString(array2)
        );

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "updated",
                  "oldValue" : "value1",
                  "newValue" : 3
                },
                {
                  "property" : "key2",
                  "status" : "updated",
                  "oldValue" : 2,
                  "newValue" : "value2"
                },
                {
                  "property" : "key3",
                  "status" : "updated",
                  "oldValue" : true,
                  "newValue" : "[2, 3, 4, 5]"
                },
                {
                  "property" : "key4",
                  "status" : "removed",
                  "value" : "[1, 2, 3, 4]"
                },
                {
                  "property" : "key5",
                  "status" : "removed",
                  "value" : "[complex value]"
                }
                ]""";

        String result = Differ.generate(map1, map2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateOneEmptyMapJson() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true,
                "key4", Arrays.toString(array1),
                "key5", nestedObj
        );

        Map<String, Object> map2 = Map.of();

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "removed",
                  "value" : "value1"
                },
                {
                  "property" : "key2",
                  "status" : "removed",
                  "value" : 2
                },
                {
                  "property" : "key3",
                  "status" : "removed",
                  "value" : true
                },
                {
                  "property" : "key4",
                  "status" : "removed",
                  "value" : "[1, 2, 3, 4]"
                },
                {
                  "property" : "key5",
                  "status" : "removed",
                  "value" : "[complex value]"
                }
                ]""";

        String result = Differ.generate(map1, map2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateBothEmptyMapsJson() {
        Map<String, Object> map1 = Map.of();
        Map<String, Object> map2 = Map.of();

        String expected = "[\n]";

        String result = Differ.generate(map1, map2, "json");
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateSameKeyDifferentValuesJson() {
        Map<String, Object> map1 = Map.of(
                "key1", Arrays.toString(array1)
        );

        Map<String, Object> map2 = Map.of(
                "key1", Arrays.toString(array2)
        );

        String expected = """
                [
                {
                  "property" : "key1",
                  "status" : "updated",
                  "oldValue" : "[1, 2, 3, 4]",
                  "newValue" : "[2, 3, 4, 5]"
                }
                ]""";

        String result = Differ.generate(map1, map2, "json");
        assertEquals(expected, result);
    }
}
