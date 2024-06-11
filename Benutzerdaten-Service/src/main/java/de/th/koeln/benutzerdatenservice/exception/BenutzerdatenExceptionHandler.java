package de.th.koeln.benutzerdatenservice.exception;

public class BenutzerdatenExceptionHandler extends RuntimeException {

    public BenutzerdatenExceptionHandler(String message) {
        super(message);
    }

    public BenutzerdatenExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
