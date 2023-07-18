package pl.karnecki.auctane.utils;

import pl.karnecki.auctane.exceptions.InvalidInputException;

public class StringToIntArrayConverter {
    public static int[] convert(String input) {
        String[] tokens = input.split("[,\\s]+");
        int[] result = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            try {
                result[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid value: " + tokens[i]);
            }
        }

        return result;
    }
}
