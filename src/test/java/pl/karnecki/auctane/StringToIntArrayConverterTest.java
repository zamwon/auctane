package pl.karnecki.auctane;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.karnecki.auctane.utils.StringToIntArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StringToIntArrayConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "1, 2, 3, 4, 5",
        "1 2 3 4 5",
        "1,2,3,4,5"})
    void convertValidInput(String input) {
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = StringToIntArrayConverter.convert(input);
        assertArrayEquals(expected, result);
    }
}
