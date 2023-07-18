package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.karnecki.auctane.accumulator.Accumulator;
import pl.karnecki.auctane.accumulator.AdditionAccumulatorImpl;

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
    }
}
