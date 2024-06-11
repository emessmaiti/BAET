package de.th.koeln.finanzdatenservices.dto;


import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

public class KontoDTO {

    private String benutzerId;
    private BigDecimal kontostand = BigDecimal.ZERO;

    public KontoDTO() {
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
