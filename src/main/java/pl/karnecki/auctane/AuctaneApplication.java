package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class AuctaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctaneApplication.class, args);

        final Accumulator accumulator = new AdditionAccumulatorImpl();
        final AppRunner appRunner = new AppRunner(accumulator);
        final Scanner scanner = new Scanner(System.in);
        appRunner.run(scanner);

//
//        int firstSum = accumulator.accumulate(1, 2, 3);
//        int secondSum = accumulator.accumulate(4);
//        log.info("firstSum = " + firstSum);
//        log.info("secondSum = " + secondSum);
//        log.info("Total = " + accumulator.getTotal());
//        log.warn("Accumulator calls reset()");
//        accumulator.reset();
//        log.info("Total after reset = " + accumulator.getTotal());

    }


}
