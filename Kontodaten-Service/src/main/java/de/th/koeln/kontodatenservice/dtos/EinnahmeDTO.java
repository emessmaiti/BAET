package de.th.koeln.kontodatenservice.dtos;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class EinnahmeDTO {

    private String benutzerID;
    private String einnahmeKategorie;
    private String bezeichnung;
    private String beschreibung;
    private LocalDate datum;
    private Long budget;
    private BigDecimal betrag;

    public EinnahmeDTO() {}


    public String getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(String benutzerID) {
        this.benutzerID = benutzerID;
    }

    public String getEinnahmeKategorie() {
        return einnahmeKategorie;
    }

    public void setEinnahmeKategorie(String einnahmeKategorie) {
        this.einnahmeKategorie = einnahmeKategorie;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }
}
