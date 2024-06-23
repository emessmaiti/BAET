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
