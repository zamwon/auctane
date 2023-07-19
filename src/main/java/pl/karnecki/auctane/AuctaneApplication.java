package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import pl.karnecki.auctane.accumulator.Accumulator;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;

@Slf4j
public class AuctaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctaneApplication.class, args);

        final Accumulator accumulator = new AdditionAccumulatorImpl();
        final AppRunner appRunner = new AppRunner(accumulator);
        appRunner.run();
    }
}
