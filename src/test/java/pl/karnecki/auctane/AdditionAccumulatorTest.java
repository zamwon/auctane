package pl.karnecki.auctane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.karnecki.auctane.accumulator.Accumulator;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionAccumulatorTest {

    private Accumulator accumulator;

    @BeforeEach
    public void setUp() {
        accumulator = new AdditionAccumulatorImpl();
    }

    @ParameterizedTest
    @MethodSource("accumulatePositiveCases")
    void shouldAccumulate(int expectedSum, int... values) {

        //when
        int sum = accumulator.accumulate(values);

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    void shouldGetTotal() {

        //given
        var prompt1 = new int[]{1, 2, 3};
        var prompt2 = new int[]{4};

        //when
        accumulator.accumulate(prompt1);
        accumulator.accumulate(prompt2);
        int total = accumulator.getTotal();

        //then
        assertEquals(10, total);
    }

    @Test
    void shouldResetAccumulator() {

        //given
        var prompt1 = new int[]{1, 2, 3};
        accumulator.accumulate(prompt1);

        //when
        accumulator.reset();
        int total = accumulator.getTotal();

        //then
        assertEquals(0, total);
    }

    @ParameterizedTest
    @MethodSource("accumulateNegativeNumbersEdgeCase")
    void shouldNotAccumulateWhenMinIntReached(int expectedSum, int[] values) {
        int sum = accumulator.accumulate(values);
        assertEquals(expectedSum, sum);
        assertEquals(0, accumulator.getTotal());
    }

    @ParameterizedTest
    @MethodSource("accumulatePositiveNumbersEdgeCase")
    void shouldNotAccumulateWhenMaxIntReached(int expectedSum, int[] values) {
        int sum = accumulator.accumulate(values);
        assertEquals(expectedSum, sum);
        assertEquals(0, accumulator.getTotal());
    }

    private static Stream<Arguments> accumulateNegativeNumbersEdgeCase() {
        return Stream.of(
            Arguments.of(Integer.MIN_VALUE, new int[]{Integer.MIN_VALUE, -1}),
            Arguments.of(-2147483548, new int[]{Integer.MIN_VALUE, 100, -101}),
            Arguments.of(-1, new int[]{-1, Integer.MIN_VALUE}),
            Arguments.of(-5, new int[]{-2, -3, Integer.MIN_VALUE}),
            Arguments.of(-70, new int[]{100, -120, -50, Integer.MIN_VALUE}),
            Arguments.of(Integer.MIN_VALUE, new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE})
        );
    }

    private static Stream<Arguments> accumulatePositiveNumbersEdgeCase() {
        return Stream.of(
            Arguments.of(Integer.MAX_VALUE, new int[]{Integer.MAX_VALUE, 1}),
            Arguments.of(2147483547, new int[]{Integer.MAX_VALUE, -100, 101}),
            Arguments.of(6, new int[]{1, 2, 3, Integer.MAX_VALUE}),
            Arguments.of(Integer.MAX_VALUE, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE})
        );
    }

    private static Stream<Arguments> accumulatePositiveCases() {
        return Stream.of(
            Arguments.of(6, new int[]{1, 2, 3}),
            Arguments.of(4, new int[]{4}),
            Arguments.of(10, new int[]{1, 2, 3, 4}),
            Arguments.of(150, new int[]{15, 30, 45, 60}),
            Arguments.of(111100, new int[]{100, 1000, 10000, 100000}),
            Arguments.of(499, new int[]{123, Integer.MIN_VALUE, -123, 500, Integer.MAX_VALUE}),
            Arguments.of(-2147483618, new int[]{100, -120, 50, -2000, 2000, Integer.MIN_VALUE}),
            Arguments.of(1, new int[]{1, Integer.MAX_VALUE}),
            Arguments.of(2147483642, new int[]{-2, -3, Integer.MAX_VALUE}),
            Arguments.of(2147483577, new int[]{100, -120, -50, Integer.MAX_VALUE})
        );
    }
}
