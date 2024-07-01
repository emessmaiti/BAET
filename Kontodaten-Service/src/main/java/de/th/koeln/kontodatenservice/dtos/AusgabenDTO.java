package de.th.koeln.kontodatenservice.dtos;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Daten-Transfer-Objekt (DTO) für Ausgaben.
 *
 * <p>Dieses DTO wird verwendet, um Ausgabeninformationen zwischen verschiedenen Schichten und Diensten zu übertragen.</p>
 */
@Embeddable
public class AusgabenDTO {

    private String benutzerID;
    private String ausgabeKategorie;
    private String bezeichnung;
    private String beschreibung;
    private LocalDate datum;
    private Long budget;
    private BigDecimal betrag;

    /**
     * Standardkonstruktor.
     */
    public AusgabenDTO() {}

    // Getter und Setter

    public String getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(String benutzerID) {
        this.benutzerID = benutzerID;
    }

    public String getAusgabeKategorie() {
        return ausgabeKategorie;
    }

    public void setAusgabeKategorie(String ausgabeKategorie) {
        this.ausgabeKategorie = ausgabeKategorie;
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
