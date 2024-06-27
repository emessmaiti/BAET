package de.th.koeln.authentifizierungservice.dto;

import java.math.BigDecimal;

/**
 * Die Klasse KontoDaten repräsentiert die Kontodaten eines Benutzers, die vom Authentifizierungsservice verwaltet werden.
 * Diese Klasse enthält die grundlegenden Informationen eines Kontos, wie die Benutzer-ID und den aktuellen Kontostand.
 *
 * <p>Die Daten dieser Klasse werden über Feign Clients von anderen Services bezogen und verarbeitet.
 * Der AuthentifizierungController verwendet Feign Clients, um Kontodaten von externen Diensten abzurufen
 * und zu speichern.</p>
 */
public class KontoDaten {

    private Long id;
    private String benutzerId;
    private BigDecimal kontostand;

    /**
     * Konstruktor zur Initialisierung der Felder benutzerId und kontostand der KontoDaten-Klasse.
     *
     * @param benutzerId Die eindeutige Benutzerkennung, der das Konto zugeordnet ist.
     * @param kontostand Der aktuelle Kontostand.
     */
    public KontoDaten(String benutzerId, BigDecimal kontostand) {
        this.benutzerId = benutzerId;
        this.kontostand = kontostand;
    }

    /**
     * Standardkonstruktor.
     */
    public KontoDaten() {}

    // Getter und Setter für alle Felder

    public Long getId() {
        return id;
    }

    public String getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(String benutzerId) {
        this.benutzerId = benutzerId;
    }

    public BigDecimal getKontostand() {
        return kontostand;
    }

    public void setKontostand(BigDecimal kontostand) {
        this.kontostand = kontostand;
    }
}
