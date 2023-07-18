package pl.karnecki.auctane.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(final String message) {
        super(message);
    }
}
