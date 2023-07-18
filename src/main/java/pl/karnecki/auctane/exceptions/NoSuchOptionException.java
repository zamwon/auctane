package pl.karnecki.auctane.exceptions;

public class NoSuchOptionException extends RuntimeException {
    public NoSuchOptionException(final String message) {
        super(message);
    }
}
