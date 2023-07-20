package pl.karnecki.auctane;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

@Slf4j
public class AuctaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctaneApplication.class, args);

        final AppRunner appRunner = new AppRunner();
        appRunner.run();
    }
}
