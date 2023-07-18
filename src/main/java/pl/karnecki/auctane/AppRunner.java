package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import pl.karnecki.auctane.utils.StringToIntArrayConverter;

import java.util.Scanner;

@Slf4j
public class AppRunner {

    private final Accumulator accumulator;


    public AppRunner(final Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    public void run(final Scanner scanner) {
        while (true) {

            logMessage("""
                Select an option:
                1. Accumulate:
                2. Get total
                3. Reset
                4. Exit
                """);

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> accumulate(scanner);
                case 2 -> getTotal();
                case 3 -> reset();
                case 4 -> exit();
                default -> throw new IllegalArgumentException();
            }
        }
    }

    private static void exit() {
        System.exit(0);
    }

    private void reset() {
        accumulator.reset();
        logMessage("Value after reset: %d".formatted(accumulator.getTotal()));
    }

    private void getTotal() {
        logMessage("Total = %d".formatted(accumulator.getTotal()));
    }

    private void accumulate(final Scanner scanner) {
        logMessage("Enter one or more values:");
        int[] values = convert(scanner.nextLine());
        accumulator.accumulate(values);
    }

    private static int[] convert(final String input) {
        return StringToIntArrayConverter.convert(input);
    }

    private void logMessage(String msg) {
        log.info(msg);
    }
}
