package de.th.koeln.finanzdatenservices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class FinanzielleZiel extends AbstraktEntitaet {

    private String bezeichnung;
    @Temporal(TemporalType.DATE)
    private LocalDate faelligkeitdatum;
    private BigDecimal sparbetrag;

    public FinanzielleZiel() {
        super();
    }

//    public FinanzielleZiel(Long version, LocalDateTime erstellerZeitstempel, LocalDateTime bearbeiterZeitstempel, String benutzerID, BigDecimal beitrag, String bezeichnung, LocalDate faelligkeitdatum, BigDecimal sparbetrag) {
//        super(version, erstellerZeitstempel, bearbeiterZeitstempel, benutzerID, beitrag);
//        this.bezeichnung = bezeichnung;
//        this.faelligkeitdatum = faelligkeitdatum;
//        this.sparbetrag = sparbetrag;
//    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDate getFaelligkeitdatum() {
        return faelligkeitdatum;
    }

    public void setFaelligkeitdatum(LocalDate faelligkeitdatum) {
        this.faelligkeitdatum = faelligkeitdatum;
    }

    public BigDecimal getSparbetrag() {
        return sparbetrag;
    }

    public void setSparbetrag(BigDecimal sparbetrag) {
        this.sparbetrag = sparbetrag;
    }
}
