package pl.karnecki.auctane.accumulator;

public class AdditionAccumulatorImpl implements Accumulator {
    private int total;

    public AdditionAccumulatorImpl() {
        this.total = 0;
    }

    @Override
    public int accumulate(int... values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
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