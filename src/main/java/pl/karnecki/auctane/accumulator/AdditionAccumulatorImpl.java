package pl.karnecki.auctane.accumulator;

import java.util.Arrays;

public class AdditionAccumulatorImpl implements Accumulator {
    private int total;

    public AdditionAccumulatorImpl() {
        this.total = 0;
    }

    @Override
    public int accumulate(int... values) {
        var sum = Arrays.stream(values).sum();
        total += sum;
        return sum;
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