package de.th.koeln.authentifizierungservice.dto;

import java.math.BigDecimal;

public class KontoDaten {

    private String benutzerId;
    private BigDecimal kontostand;

    public KontoDaten(String benutzerId, BigDecimal kontostand) {
        this.benutzerId = benutzerId;
        this.kontostand = kontostand;
    }

    public KontoDaten(){}

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
