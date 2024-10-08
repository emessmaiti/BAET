package de.th.koeln.finanzdatenservices.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Die Klasse FinanzielleZiel repräsentiert ein finanzielles Ziel, das in der Anwendung verwaltet wird.
 * Sie erbt von der abstrakten Klasse {@link AbstraktEntitaet}, die gemeinsame Eigenschaften und Verhalten definiert.
 *
 * <p>Diese Klasse enthält spezifische Eigenschaften eines finanziellen Ziels wie Bezeichnung, Fälligkeitsdatum und Sparbetrag.</p>
 */
@Entity
@Table(name = "finanzielle_ziel", indexes = {
        @Index(name = "idx_bezeichnung", columnList = "bezeichnung"),
        @Index(name = "idx_faelligkeitdatum", columnList = "faelligkeitdatum"),
        @Index(name = "idx_sparbetrag", columnList = "sparbetrag"),
        @Index(name = "idx_benutzerID", columnList = "benutzerID"),
        @Index(name = "idx_kontoId", columnList = "kontoId"),
        @Index(name = "idx_betrag", columnList = "betrag")
})
public class FinanzielleZiel extends AbstraktEntitaet {

    private String bezeichnung;

    @Column(nullable = false)
    private LocalDate faelligkeitdatum;

    @Column(nullable = false)
    private BigDecimal sparbetrag;

    /**
     * Standardkonstruktor.
     */
    public FinanzielleZiel() {
        super();
    }

    // Getter und Setter für alle Felder

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
