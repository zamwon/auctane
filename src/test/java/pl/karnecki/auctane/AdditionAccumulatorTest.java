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
    @MethodSource("provideTestCases")
    void shouldAccumulate(int expectedSum, int... values) {

        //when
        int sum = accumulator.accumulate(values);

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    void shouldGetTotal() {

        //given
        var prompt1 = new int[]{1,2,3};
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
        var prompt1 = new int[]{1,2,3};
        accumulator.accumulate(prompt1);

        //when
        accumulator.reset();
        int total = accumulator.getTotal();

        //then
        assertEquals(0, total);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            Arguments.of(6, 1, 2, 3),
            Arguments.of(4, 4),
            Arguments.of(10, 1, 2, 3, 4)
        );
    }
}
