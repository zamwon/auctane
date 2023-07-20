package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;
import pl.karnecki.auctane.utils.ConsoleLogger;

import java.util.Scanner;

import static pl.karnecki.auctane.utils.StringToIntArrayConverter.convert;

@Slf4j
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
    public static final String PROVIDE_NUMBER = "Please provide number - input is incorrect. ";
    public static final String SELECT_OPTION = "Select option from 1 to 4!";
    private static final Integer LIMIT = 10000;
    public static final String ENTER_ONE_OR_MORE_NUMBERS = "Enter one or more numbers: ";
    public static final String INCORRECT_VALUE = " Incorrect value: ";
    private final AdditionAccumulatorImpl accumulator;
    private final Scanner scanner;
    private final ConsoleLogger consoleLogger;

    public AppRunner() {
        this.accumulator = new AdditionAccumulatorImpl();
        this.scanner = new Scanner(System.in);
        this.consoleLogger = new ConsoleLogger();
    }

    public void run() {
        var counter = 0;
        while (counter++ < LIMIT) {
            getPrompt();
        }
    }

    private void getPrompt() {
        consoleLogger.logMessage(PROMPT_MENU);
        try {
            var option = scanner.nextLine();
            switch (option) {
                case "1" -> accumulate();
                case "2" -> getTotal();
                case "3" -> reset();
                case "4" -> exit();
                default -> consoleLogger.logWarnMessage(SELECT_OPTION, option);
            }
        } catch (IllegalArgumentException e) {
            consoleLogger.logWarnMessage(PROVIDE_NUMBER + e);
        }
    }

    private void accumulate() {
        consoleLogger.logMessage(ENTER_ONE_OR_MORE_NUMBERS);
        var values = convert(scanner.nextLine());
        accumulator.accumulate(values);
    }

    private void getTotal() {
        consoleLogger.logMessage(TOTAL, accumulator.evaluateTotal());
    }

    private void reset() {
        accumulator.reset();
        consoleLogger.logMessage(VALUE_AFTER_RESET, accumulator.getTotal());
    }

    private void exit() {
        System.exit(0);
    }
}
