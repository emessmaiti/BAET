package de.th.koeln.finanzdatenservices.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Die Klasse Ausgabe repräsentiert eine finanzielle Ausgabe, die in der Anwendung verwaltet wird.
 * Sie erbt von der abstrakten Klasse {@link AbstraktEntitaet}, die gemeinsame Eigenschaften und Verhalten definiert.
 *
 * <p>Diese Klasse enthält spezifische Eigenschaften einer Ausgabe wie Kategorie, Bezeichnung, Beschreibung und Datum.
 * Zusätzlich wird eine Beziehung zu einem Budget definiert, dem die Ausgabe zugeordnet ist.</p>
 */
@Entity
@Table(name = "ausgabe", indexes = {
        @Index(name = "idx_ausgabeKategorie", columnList = "ausgabeKategorie"),
        @Index(name = "idx_bezeichnung", columnList = "bezeichnung"),
        @Index(name = "idx_datum", columnList = "datum"),
        @Index(name = "idx_budget_id", columnList = "budget_id"),
        @Index(name = "idx_benutzerID", columnList = "benutzerID"),
        @Index(name = "idx_kontoId", columnList = "kontoId"),
        @Index(name = "idx_betrag", columnList = "betrag")
})
public class Ausgabe extends AbstraktEntitaet {

    @Enumerated(EnumType.STRING)
    private AusgabeKategorie ausgabeKategorie;

    private String bezeichnung;
    private String beschreibung;

    @Column(nullable = false)
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    @JsonBackReference
    private Budget budget;

    /**
     * Standardkonstruktor.
     */
    public Ausgabe() {
        super();
    }

    // Getter und Setter für alle Felder

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

    /**
     * Methode, die vor dem Entfernen der Entität aufgerufen wird, um die Ausgabe aus dem Budget zu entfernen und den Restbetrag zu aktualisieren.
     */
    @PreRemove
    private void removeFromBudget() {
        if (budget != null) {
            budget.getAusgaben().remove(this);
            budget.setRestBetrag(budget.getRestBetrag().add(this.getBetrag()));
        }
    }
}
