package de.th.koeln.finanzdatenservices.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse Budget repräsentiert ein Budget, das in der Anwendung verwaltet wird.
 * Sie erbt von der abstrakten Klasse {@link AbstraktEntitaet}, die gemeinsame Eigenschaften und Verhalten definiert.
 *
 * <p>Diese Klasse enthält spezifische Eigenschaften eines Budgets wie Kategorie, Startdatum, Enddatum und Restbetrag.
 * Zusätzlich wird eine Beziehung zu mehreren Ausgaben definiert, die diesem Budget zugeordnet sind.</p>
 */
@Entity
@Table(name = "budget", indexes = {
        @Index(name = "idx_kategorie", columnList = "kategorie"),
        @Index(name = "idx_startDatum", columnList = "startDatum"),
        @Index(name = "idx_endDatum", columnList = "endDatum"),
        @Index(name = "idx_restBetrag", columnList = "restBetrag"),
        @Index(name = "idx_benutzerID", columnList = "benutzerID"),
        @Index(name = "idx_kontoId", columnList = "kontoId"),
        @Index(name = "idx_betrag", columnList = "betrag")
})
public class Budget extends AbstraktEntitaet {

    @Enumerated(EnumType.STRING)
    private AusgabeKategorie kategorie;

    @Column(nullable = false)
    private LocalDate startDatum;

    @Column(nullable = false)
    private LocalDate endDatum;

    @Column(nullable = false)
    private BigDecimal restBetrag;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Ausgabe> ausgaben = new HashSet<>();

    @Transient
    private BigDecimal progress;

    /**
     * Standardkonstruktor.
     */
    public Budget() {
        super();
    }

    // Getter und Setter für alle Felder

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

    public void setAusgaben(Set<Ausgabe> ausgaben) {
        this.ausgaben = ausgaben;
    }

    public BigDecimal getProgress() {
        return this.progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }
}
