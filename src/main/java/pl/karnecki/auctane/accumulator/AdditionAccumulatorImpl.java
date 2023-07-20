package pl.karnecki.auctane.accumulator;

import pl.karnecki.auctane.utils.ConsoleLogger;

import java.math.BigInteger;

public class AdditionAccumulatorImpl implements Accumulator {
    public static final String UNDERFLOW = "Underflow occurred - data might be lost! Switching to BigInteger";
    public static final String OVERFLOW = "Overflow occurred - data might be lost! Switching to BigInteger";
    public static final String RESULT = "Accumulate result = ";
    private final ConsoleLogger consoleLogger;
    private BigInteger total;

    public AdditionAccumulatorImpl() {
        this.consoleLogger = new ConsoleLogger();
        this.total = BigInteger.ZERO;
    }

    private static boolean isMaxIntReached(BigInteger sum, int value) {
        return value > 0 && (sum.add(BigInteger.valueOf(value)).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0);
    }

    private static boolean isMinIntReached(BigInteger sum, int value) {
        return value < 0 && (sum.add(BigInteger.valueOf(value)).compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) <= 0);
    }

    @Override
    public int accumulate(int... values) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < values.length; i++) {

            if (isMaxIntReached(sum, values[i]) || isMinIntReached(sum, values[i])) {
                BigInteger[] bigIntValues = new BigInteger[values.length];
                bigIntValues[i] = BigInteger.valueOf(values[i]);
            }
            sum = sum.add(BigInteger.valueOf(values[i]));
        }
        total = total.add(sum);
        consoleLogger.logMessage(RESULT + sum);

        return returnResult();
    }

    @Override
    public int getTotal() {
        return total.intValueExact();
    }

    @Override
    public void reset() {
        total = BigInteger.ZERO;
    }

    private boolean isUnderFlow() {
        consoleLogger.logWarnMessage(UNDERFLOW);
        return total.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
    }

    public BigInteger getTotal(BigInteger total) {
        return total;
    }

    public Object evaluateTotal() {
        if (isOverflow() || isUnderFlow()) {
            return getTotal(total);
        } else return getTotal();
    }

    private boolean isOverflow() {
        consoleLogger.logWarnMessage(OVERFLOW);
        return total.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0;
    }

    private int returnResult() {
        if (isUnderFlow()) {
            return Integer.MIN_VALUE;
        } else if (isOverflow()) {
            return Integer.MAX_VALUE;
        } else return total.intValueExact();
    }
}
