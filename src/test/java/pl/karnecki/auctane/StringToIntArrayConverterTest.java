package pl.karnecki.auctane;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.karnecki.auctane.utils.StringToIntArrayConverter;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringToIntArrayConverterTest {

    @ParameterizedTest
    @MethodSource("parseStringProvider")
    void convertValidInput(int[] expected, String input) {

        //when
        var result = StringToIntArrayConverter.convert(input);

        //then
        assertArrayEquals(expected, (int[]) result);
    }

    @ParameterizedTest
    @MethodSource("parseStringBigIntProvider")
    void convertValidBigInput(BigInteger[] expected, String input) {

        //when
        var result = StringToIntArrayConverter.convert(input);

        //then
        assertArrayEquals(expected, (BigInteger[]) result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "1, 2, abc, 4, 5",
        "a, b, c, d, e, f",
        "1; a; 2; b; 3; c",
        "1 2 3 a b c d e f 1 2 3",
        "1 41241241241241241241; adb",
        "! @ #",
        "1 * 5 --5"
    })
    void throwInvalidInputExceptionWhenInvalidInput(String input) {
        assertThrows(NumberFormatException.class, () ->
            StringToIntArrayConverter.convert(input));
    }

    private static Stream<Arguments> parseStringProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, "1, 2, 3, 4, 5, 6"),
            Arguments.of(new int[]{1, 2, 4, 5}, "1 2 4 5"),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7}, "1, 2; 3 4,5;6;7"),
            Arguments.of(new int[]{1, 2, 3, 4, 5}, "1;2;3;4;5"),
            Arguments.of(new int[]{1, 2, 3, 4, 5}, "1; 2; 3; 4; 5"),
            Arguments.of(new int[]{1, 2, 3, 4, 5}, "1,2,3,4,5")
        );
    }

    private static Stream<Arguments> parseStringBigIntProvider() {
        return Stream.of(
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(1)}, "999999999999999; 1"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(1)}, "999999999999999, 1"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(1)}, "999999999999999 1"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(999999999999999L)}, "1, 999999999999999"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(999999999999999L)}, "1; 999999999999999"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(999999999999999L)}, "1 999999999999999"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(999999999999999L)}, "1,999999999999999"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(1), BigInteger.valueOf(999999999999999L)}, "1;999999999999999"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(2222222222222L)}, "999999999999999, 2222222222222"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(2222222222222L)}, "999999999999999,2222222222222"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(2222222222222L)}, "999999999999999;2222222222222"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(2222222222222L)}, "999999999999999; 2222222222222"),
            Arguments.of(new BigInteger[]{BigInteger.valueOf(999999999999999L), BigInteger.valueOf(2222222222222L)}, "999999999999999 2222222222222")
        );
    }
}
