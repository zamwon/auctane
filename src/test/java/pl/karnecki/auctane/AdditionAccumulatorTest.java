package pl.karnecki.auctane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionAccumulatorTest {

    private AdditionAccumulatorImpl accumulator;

    @BeforeEach
    public void setUp() {
        accumulator = new AdditionAccumulatorImpl();
    }

    @ParameterizedTest
    @MethodSource("accumulateTestCasesProvider")
    void shouldAccumulate(final BigInteger expectedSum, int... values) {

        //when
        int sum = accumulator.accumulate(values);

        //then
        assertEquals(expectedSum, BigInteger.valueOf(sum));
    }

    @ParameterizedTest
    @MethodSource("accumulateOverFlowProvider")
    void shouldAccumulateWhenOverFlowAndReturnedMaxInt(final BigInteger expectedSum, int... values) {

        //when
        int sum = accumulator.accumulate(values);

        //then
        assertEquals(expectedSum, BigInteger.valueOf(sum));
    }
    @ParameterizedTest
    @MethodSource("overrideTotalProvider")
    void shouldOverwriteTotalValueWhenOverFlow(final BigInteger expectedSum, int... values) {

        //when
        accumulator.accumulate(values);
        var total = accumulator.evaluateTotal();

        //then
        assertEquals(expectedSum, total);
    }

    @ParameterizedTest
    @MethodSource("accumulateUnderFlowProvider")
    void shouldAccumulateWhenUnderFlowAndReturnedMinInt(final BigInteger expectedSum, int... values) {

        //when
        int sum = accumulator.accumulate(values);

        //then
        assertEquals(expectedSum, BigInteger.valueOf(sum));
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

    private static Stream<Arguments> accumulateTestCasesProvider() {
        return Stream.of(
            Arguments.of(BigInteger.valueOf(4), new int[]{4}),
            Arguments.of(BigInteger.valueOf(98), new int[]{100, -101, 5, -5, 99}),
            Arguments.of(BigInteger.valueOf(111111), new int[]{1, 10, 100, 1000, 10000, 100000}),
            Arguments.of(BigInteger.valueOf(276549), new int[]{100, -142142, -50, 418641}),
            Arguments.of(BigInteger.valueOf(499), new int[]{123, Integer.MIN_VALUE, -123, 500, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(2147483642), new int[]{Integer.MAX_VALUE, -2, -3}),
            Arguments.of(BigInteger.valueOf(2147483577), new int[]{100, -120, -50, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(-1086926), new int[]{-412312, 1000, -675756, 142}),
            Arguments.of(BigInteger.valueOf(-352), new int[]{100, -200, -50, -202}),
            Arguments.of(BigInteger.valueOf(-747), new int[]{-123, Integer.MIN_VALUE, -123, -500, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(-111111), new int[]{-1, -10, -100, -1000, -10000, -100000})
        );
    }

    private static Stream<Arguments> accumulateOverFlowProvider() {
        return Stream.of(
            Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), new int[]{Integer.MAX_VALUE, 1}),
            Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), new int[]{1, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), new int[]{111, 222, 333, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MAX_VALUE), new int[]{10000, Integer.MAX_VALUE, -200})
        );
    }

    private static Stream<Arguments> accumulateUnderFlowProvider() {
        return Stream.of(
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{Integer.MIN_VALUE, -1}),
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{-1, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{-2, -3, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{Integer.MIN_VALUE, 100, -120, -50}),
            Arguments.of(BigInteger.valueOf(Integer.MIN_VALUE), new int[]{100, -120, 50, Integer.MIN_VALUE, -3000, 2000})
        );
    }

    private static Stream<Arguments> overrideTotalProvider() {
        return Stream.of(
            Arguments.of(BigInteger.valueOf(2147483648L), new int[]{Integer.MAX_VALUE, 1}),
            Arguments.of(BigInteger.valueOf(2147483648L), new int[]{1, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(4294967294L), new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(2147484313L), new int[]{111, 222, 333, Integer.MAX_VALUE}),
            Arguments.of(BigInteger.valueOf(2147493447L), new int[]{10000, Integer.MAX_VALUE, -200}),

            Arguments.of(BigInteger.valueOf(-2147483649L), new int[]{Integer.MIN_VALUE, -1}),
            Arguments.of(BigInteger.valueOf(-2147483649L), new int[]{-1, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(-4294967296L), new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(-2147483653L), new int[]{-2, -3, Integer.MIN_VALUE}),
            Arguments.of(BigInteger.valueOf(-2147483718L), new int[]{Integer.MIN_VALUE, 100, -120, -50}),
            Arguments.of(BigInteger.valueOf(-2147484618L), new int[]{100, -120, 50, Integer.MIN_VALUE, -3000, 2000})
        );
    }


}
