package de.th.koeln.finanzdatenservices.entities;

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
    private Budget budget;

    public Ausgabe() {
        super();
    }

//    public Ausgabe(Long version, LocalDateTime erstellerZeitstempel, LocalDateTime bearbeiterZeitstempel, String benutzerID, BigDecimal beitrag, AusgabeKategorie ausgabeKategorie,
//                   String bezeichnung, String beschreibung, LocalDate datum, Budget budget) {
//        super(version, erstellerZeitstempel, bearbeiterZeitstempel, benutzerID, beitrag);
//        this.ausgabeKategorie = ausgabeKategorie;
//        this.bezeichnung = bezeichnung;
//        this.beschreibung = beschreibung;
//        this.datum = datum;
//        this.budget = budget;
//    }

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
}

