package pl.karnecki.auctane;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.karnecki.auctane.exceptions.InvalidInputException;
import pl.karnecki.auctane.utils.StringToIntArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "1, 2, abc, 4, 5",
        "a, b, c, d, e",
        "1, a, 2, b, 3, c"})
    void throwInvalidInputExceptionWhenInvalidInput(String input) {
        assertThrows(InvalidInputException.class, () ->
            StringToIntArrayConverter.convert(input));
    }
}
