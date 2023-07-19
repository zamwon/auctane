package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.karnecki.auctane.accumulator.Accumulator;

import java.util.Scanner;

import static pl.karnecki.auctane.utils.StringToIntArrayConverter.convert;

@Slf4j
@Component
public class AppRunner {

    public static final String PROMPT_MENU = """
        Select an option:
        1. Accumulate:
        2. Get total
        3. Reset
        4. Exit
        """;
    public static final String TOTAL = "Total = ";
    public static final String VALUE_AFTER_RESET = "Value after reset: ";
    public static final String PROVIDE_NUMBER = "Provide number! Format incorrect! ";
    public static final String SELECT_OPTION = "Select option from 1 to 4!";
    private static final Integer LIMIT = 10000;
    public static final String ENTER_ONE_OR_MORE_VALUES = "Enter one or more values: ";
    private final Accumulator accumulator;
    private final Scanner scanner;

    public AppRunner(final Accumulator accumulator) {
        this.accumulator = accumulator;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        var counter = 0;
        while (counter++ < LIMIT) {
            getPrompt();
        }
    }

    private void getPrompt() {
        logMessage(PROMPT_MENU);
        try {
            var option = scanner.nextLine();
            switch (option) {
                case "1" -> accumulate();
                case "2" -> getTotal();
                case "3" -> reset();
                case "4" -> exit();
                default -> logWarnMessage(SELECT_OPTION, option);
            }
        } catch (IllegalArgumentException e) {
            logWarnMessage(PROVIDE_NUMBER + e);
        }
    }

    private void accumulate() {
        logMessage(ENTER_ONE_OR_MORE_VALUES);
        var values = convert(scanner.nextLine());
        accumulator.accumulate(values);
    }

    private void getTotal() {
        logMessage(TOTAL, accumulator.getTotal());
    }

    private void reset() {
        accumulator.reset();
        logMessage(VALUE_AFTER_RESET, accumulator.getTotal());
    }

    private void exit() {
        System.exit(0);
    }

    private void logMessage(final String msg, final Object o) {
        log.info(msg + o);
    }

    private void logMessage(final String msg) {
        log.info(msg);
    }

    private void logWarnMessage(final String msg) {
        log.warn(msg);
    }

    private void logWarnMessage(final String msg, final Object o) {
        log.warn(msg + " Incorrect value: " + o);
    }
}
