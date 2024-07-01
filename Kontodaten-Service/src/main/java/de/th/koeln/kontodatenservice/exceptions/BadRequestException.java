package de.th.koeln.kontodatenservice.exceptions;

/**
 * Ausnahme für fehlerhafte Anfragen.
 *
 * <p>Diese Ausnahme wird ausgelöst, wenn eine fehlerhafte Anfrage empfangen wird.
 * Sie erweitert die RuntimeException und kann mit einer benutzerdefinierten Fehlermeldung initialisiert werden.</p>
 */
public class BadRequestException extends RuntimeException {

    /**
     * Konstruktor zur Initialisierung der Ausnahme mit einer Fehlermeldung.
     *
     * @param message Die Fehlermeldung.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
