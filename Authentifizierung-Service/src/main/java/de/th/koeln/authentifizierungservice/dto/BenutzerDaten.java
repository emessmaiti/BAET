package de.th.koeln.authentifizierungservice.dto;

import java.time.LocalDateTime;

/**
 * Die Klasse BenutzerDaten repräsentiert die Benutzerdaten, die vom Authentifizierungsservice verwaltet werden.
 * Diese Klasse enthält die grundlegenden Informationen eines Benutzers, wie Vorname, Nachname, E-Mail,
 * Geschlecht und eine eindeutige Benutzerkennung (sub).
 *
 * <p>Die Daten dieser Klasse werden über Feign Clients von anderen Services bezogen und verarbeitet.
 * Der AuthentifizierungController verwendet Feign Clients, um Benutzerdaten von externen Diensten abzurufen
 * und zu speichern.</p>
 */
public class BenutzerDaten {

    private Long id;
    private String email;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private String sub;
    private LocalDateTime letzteAnmeldung;


    /**
     * Standardkonstruktor.
     */
    public BenutzerDaten() {
    }

    /**
     * Konstruktor zur Initialisierung aller Felder der BenutzerDaten-Klasse.
     *
     * @param vorname         Der Vorname des Benutzers.
     * @param nachname        Der Nachname des Benutzers.
     * @param email           Die E-Mail-Adresse des Benutzers.
     * @param geschlecht      Das Geschlecht des Benutzers.
     * @param sub             Die eindeutige Benutzerkennung.
     * @param letzteAnmeldung Das Datum und die Uhrzeit der letzten Anmeldung des Benutzers.
     */
    public BenutzerDaten(String vorname, String nachname, String email, String geschlecht, String sub, LocalDateTime letzteAnmeldung) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.geschlecht = geschlecht;
        this.sub = sub;
        this.letzteAnmeldung = letzteAnmeldung;
    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
