package pl.karnecki.auctane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.karnecki.auctane.accumulator.Accumulator;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionAccumulatorTest {

    private Accumulator accumulator;

    @BeforeEach
    public void setUp() {
        accumulator = new AdditionAccumulatorImpl();
    }

    @Test
    void testAccumulate() {
        int firstSum = accumulator.accumulate(1, 2, 3);
        assertEquals(6, firstSum);

        int secondSum = accumulator.accumulate(4);
        assertEquals(4, secondSum);
    }

    @Test
    void testGetTotal() {
        accumulator.accumulate(1, 2, 3);
        int total = accumulator.getTotal();
        assertEquals(6, total);

        accumulator.accumulate(4);
        total = accumulator.getTotal();
        assertEquals(10, total);
    }

    @Test
    void testReset() {
        accumulator.accumulate(1, 2, 3);
        accumulator.reset();
        int total = accumulator.getTotal();
        assertEquals(0, total);
    }
}
