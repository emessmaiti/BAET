package de.th.koeln.finanzdatenservices.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Ausgabe extends AbstraktEntitaet {

    @Enumerated(EnumType.STRING)
    private AusgabeKategorie ausgabeKategorie;
    private String bezeichnung;
    private String beschreibung;
    @Temporal(TemporalType.DATE)
    private LocalDate datum;
    @ManyToOne
    @JoinColumn(name = "budget_id")
    @JsonBackReference
    private Budget budget;

    public Ausgabe() {
        super();
    }

    @NonNull
    public AusgabeKategorie getAusgabeKategorie() {
        return ausgabeKategorie;
    }

    public void setAusgabeKategorie(@NonNull AusgabeKategorie ausgabeKategorie) {
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @PreRemove
    private void removeFromBudget() {
        if (budget != null) {
            budget.getAusgaben().remove(this);
            budget.setRestBetrag(budget.getRestBetrag().add(this.getBetrag()));
        }
    }
}

