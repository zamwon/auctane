package pl.karnecki.auctane.accumulator;

import pl.karnecki.auctane.utils.ConsoleLogger;

public class AdditionAccumulatorImpl implements Accumulator {
    private final ConsoleLogger consoleLogger;
    private int total;


    public AdditionAccumulatorImpl() {
        this.consoleLogger = new ConsoleLogger();
        this.total = 0;
    }

    @Override
    public int accumulate(int... values) {
        var sum = 0;

        for (int value : values) {
            if (isMaxIntReached(sum, value) || isMinIntReached(sum, value)) {
                consoleLogger.logWarnMessage("Overflow occurred - data might be lost! Accumulation cancelled");
                return sum;
            }
            sum += value;
        }
        total += sum;
        return sum;
    }

    private static boolean isMinIntReached(final int sum, final int value) {
        return value < 0 && sum < Integer.MIN_VALUE - value;
    }

    private static boolean isMaxIntReached(final int sum, final int value) {
        return value > 0 && sum > Integer.MAX_VALUE - value;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public void reset() {
        total = 0;
    }
}