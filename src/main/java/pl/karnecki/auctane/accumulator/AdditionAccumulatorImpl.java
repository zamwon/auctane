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

    @Override
    public int accumulate(int... values) {
        var sum = BigInteger.ZERO;
        for (int value : values) {

            sum = sum.add(BigInteger.valueOf(value));
        }
        total = total.add(sum);
        consoleLogger.logMessage(RESULT + sum);

        return total.intValueExact();
    }

    @Override
    public int getTotal() {
        return total.intValueExact();
    }

    @Override
    public void reset() {
        total = BigInteger.ZERO;
    }

    public BigInteger getTotal(BigInteger total) {
        return total;
    }

    public Object evaluateTotal() {
        if (isOverflow() || isUnderFlow()) {
            return getTotal(total);
        }
        return getTotal();
    }

    private boolean isOverflow() {
        return total.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0;
    }

    private boolean isUnderFlow() {
        return total.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
    }

    public BigInteger accumulate(final BigInteger[] values) {
        var sum = BigInteger.ZERO;
        for (BigInteger value : values) {

            sum = sum.add(value);
        }
        total = total.add(sum);
        consoleLogger.logMessage(RESULT + sum);
        return total;
    }
}
