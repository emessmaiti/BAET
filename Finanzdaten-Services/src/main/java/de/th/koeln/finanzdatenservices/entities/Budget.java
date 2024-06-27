package de.th.koeln.finanzdatenservices.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Budget extends AbstraktEntitaet {

    @Enumerated(EnumType.STRING)
    private AusgabeKategorie kategorie;
    @Temporal(TemporalType.DATE)
    private LocalDate startDatum;
    @Temporal(TemporalType.DATE)
    private LocalDate endDatum;
    private BigDecimal restBetrag;
    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Ausgabe> ausgaben = new HashSet<Ausgabe>();
    @Transient
    private BigDecimal progress;

    public Budget() {
        super();
    }

    public AusgabeKategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(AusgabeKategorie kategorie) {
        this.kategorie = kategorie;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public LocalDate getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(LocalDate endDatum) {
        this.endDatum = endDatum;
    }

    public BigDecimal getRestBetrag() {
        return restBetrag;
    }

    public void setRestBetrag(BigDecimal restBetrag) {
        this.restBetrag = restBetrag;
    }

    public Set<Ausgabe> getAusgaben() {
        return ausgaben;
    }

    public void setAusgaben(Set<Ausgabe> ausgabe) {
        this.ausgaben = ausgabe;
    }

    public BigDecimal getProgress(){
        return this.progress;
    }

    public void setProgress(BigDecimal progress){
        this.progress = progress;
    }
}
