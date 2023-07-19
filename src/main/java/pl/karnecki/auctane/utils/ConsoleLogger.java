package pl.karnecki.auctane.utils;

import lombok.extern.slf4j.Slf4j;

import static pl.karnecki.auctane.AppRunner.INCORRECT_VALUE;

@Slf4j
public class ConsoleLogger {
    public void logMessage(final String msg, final Object o) {
        log.info(msg + o);
    }

    public void logMessage(final String msg) {
        log.info(msg);
    }

    public void logWarnMessage(final String msg) {
        log.warn(msg);
    }

    public void logWarnMessage(final String msg, final Object o) {
        log.warn(msg + INCORRECT_VALUE + o);
    }
}
