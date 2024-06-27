package de.th.koeln.benutzerdatenservice.services;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;

/**
 * Das Service-Interface für die Benutzerdatenverwaltung.
 *
 * <p>Dieses Interface definiert die grundlegenden Methoden zur Speicherung, Löschung,
 * und Aktualisierung von Benutzerdaten sowie zum Abrufen von Benutzerdaten anhand verschiedener Kriterien.</p>
 */
public interface IBenutzerdatenServices {

    /**
     * Speichert die angegebenen Benutzerdaten.
     *
     * @param benutzer Die zu speichernden Benutzerdaten.
     * @return Die gespeicherten Benutzerdaten.
     */
    Benutzerdaten speichern(Benutzerdaten benutzer);

    /**
     * Löscht die Benutzerdaten mit der angegebenen ID.
     *
     * @param id Die ID der zu löschenden Benutzerdaten.
     */
    void loeschen(long id);

    /**
     * Findet einen Benutzer anhand der E-Mail-Adresse.
     *
     * @param email Die E-Mail-Adresse des Benutzers.
     * @return Die Benutzerdaten des Benutzers.
     */
    Benutzerdaten findBenutzerByEmail(String email);

    /**
     * Findet einen Benutzer anhand der ID.
     *
     * @param id Die ID des Benutzers.
     * @return Die Benutzerdaten des Benutzers.
     */
    Benutzerdaten findBenutzerById(Long id);

    /**
     * Aktualisiert die angegebenen Benutzerdaten.
     *
     * @param benutzer Die zu aktualisierenden Benutzerdaten.
     * @return Die aktualisierten Benutzerdaten.
     */
    Benutzerdaten updateBenutzer(Benutzerdaten benutzer);

    /**
     * Findet einen Benutzer anhand der Sub (Subject-ID).
     *
     * @param sub Die Subject-ID des Benutzers.
     * @return Die Benutzerdaten des Benutzers.
     */
    Benutzerdaten findBenutzerBySub(String sub);

    /**
     * Aktualisiert das Datum und die Uhrzeit der letzten Anmeldung eines Benutzers anhand der Sub (Subject-ID).
     *
     * @param sub Die Subject-ID des Benutzers.
     */
    void updateLastLogin(String sub);
}
