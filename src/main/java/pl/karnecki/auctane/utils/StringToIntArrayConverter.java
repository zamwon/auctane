package pl.karnecki.auctane.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Arrays;

@Slf4j
public class StringToIntArrayConverter {
    public static Object convert(final String input) {
        var elements = input.split("[,;\\s]+");
        var isAllIntegers = Arrays.stream(elements)
            .allMatch(value -> {
                try {
                    Integer.parseInt(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            });

        if (isAllIntegers) {
            return Arrays.stream(elements)
                .mapToInt(Integer::parseInt)
                .toArray();
        } return Arrays.stream(elements)
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
    }
}
