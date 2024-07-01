package de.th.koeln.finanzdatenservices.dto;

import java.math.BigDecimal;

/**
 * DTO für Kontodaten.
 */
public class KontoDTO {

    private String benutzerId;
    private BigDecimal kontostand = BigDecimal.ZERO;

    /**
     * Standardkonstruktor.
     */
    public KontoDTO() {
    }

    // Getter und Setter

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
