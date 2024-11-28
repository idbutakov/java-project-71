import hexlet.code.Differ;
import hexlet.code.Difference;
import hexlet.code.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTests {

    @Test
    public void testGenerateIdenticalMaps() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true
        );
        Map<String, Object> map2 = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true
        );

        String expected = "{\n"
                + "    key1: value1\n"
                + "    key2: 2\n"
                + "    key3: true\n"
                + "}";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateDifferentMaps() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2
        );
        Map<String, Object> map2 = Map.of(
                "key2", 3,
                "key3", "new value"
        );

        String expected = "{\n"
                + "  - key1: value1\n"
                + "  - key2: 2\n"
                + "  + key2: 3\n"
                + "  + key3: new value\n"
                + "}";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateOneEmptyMap() {
        Map<String, Object> map1 = Map.of(
                "key1", "value1",
                "key2", 2
        );
        Map<String, Object> map2 = Map.of();

        String expected = "{\n"
                + "  - key1: value1\n"
                + "  - key2: 2\n"
                + "}";

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
        Map<String, Object> map1 = Map.of(
                "key1", "value1"
        );
        Map<String, Object> map2 = Map.of(
                "key1", "value2"
        );

        String expected = "{\n"
                + "  - key1: value1\n"
                + "  + key1: value2\n"
                + "}";

        Set<Difference> result = Differ.generate(map1, map2);
        String formattedResult = StylishFormatter.format(result);
        assertEquals(expected, formattedResult);
    }
}
