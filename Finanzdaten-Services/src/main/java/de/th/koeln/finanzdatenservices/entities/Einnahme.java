package de.th.koeln.finanzdatenservices.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import java.time.LocalDate;


@Entity
public class Einnahme extends AbstraktEntitaet {

    @Enumerated(EnumType.STRING)
    private EinnahmeKategorie einnahmeKategorie;
    private String bezeichnung;
    private String beschreibung;
    @Temporal(TemporalType.DATE)
    private LocalDate datum;

    public Einnahme() {
        super();
    }

//    public Einnahme(Long version, LocalDateTime erstellerZeitstempel, LocalDateTime bearbeiterZeitstempel,
//                    String benutzerID, BigDecimal beitrag, EinnahmeKategorie einnahmeKategorie, String bezeichnung, String beschreibung, LocalDate datum) {
//        super(version, erstellerZeitstempel, bearbeiterZeitstempel, benutzerID, beitrag);
//        this.einnahmeKategorie = einnahmeKategorie;
//        this.bezeichnung = bezeichnung;
//        this.beschreibung = beschreibung;
//        this.datum = datum;
//    }

    @NonNull
    public EinnahmeKategorie getEinnahmeKategorie() {
        return einnahmeKategorie;
    }

    public void setEinnahmeKategorie(@NonNull EinnahmeKategorie einnahmeKategorie) {
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
}
