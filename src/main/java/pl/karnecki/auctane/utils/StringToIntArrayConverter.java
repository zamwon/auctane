package pl.karnecki.auctane.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class StringToIntArrayConverter {
    public static int[] convert(String input) {
        return Arrays.stream(input.split("[,\\s]+"))
            .mapToInt(value -> {
                try {
                    return Integer.parseInt(value);
                } catch (IllegalArgumentException e) {
                    logWarnMessage(value);
                    throw e;
                }
            })
            .toArray();
    }

    private static void logWarnMessage(final String value) {
        log.warn("Cannot cast " + value + " to integer");
    }
}
