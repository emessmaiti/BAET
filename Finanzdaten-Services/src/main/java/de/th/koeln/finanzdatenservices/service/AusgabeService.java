package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.client.KontoClient;
import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.repository.AusgabeRepository;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Der Service für die Verwaltung von Ausgaben.
 *
 * <p>Dieser Service erweitert {@link BaseService} und bietet zusätzliche Methoden zur spezifischen Verwaltung von Ausgaben.</p>
 */
@Service
public class AusgabeService extends BaseService<Ausgabe> {

    protected AusgabeRepository repository;
    protected KontoClient kontoClient;
    protected BudgetService budgetService;

    /**
     * Konstruktor zur Initialisierung des Repositories, des KontoClients und des BudgetService.
     *
     * @param repository Das Repository zur Verwaltung der Ausgaben.
     * @param kontoClient Der Client zur Interaktion mit dem Konto-Service.
     * @param budgetService Der Service zur Verwaltung von Budgets.
     */
    @Autowired
    protected AusgabeService(BaseRepository<Ausgabe> repository, KontoClient kontoClient, BudgetService budgetService) {
        super(repository);
        this.repository = (AusgabeRepository) repository;
        this.kontoClient = kontoClient;
        this.budgetService = budgetService;
    }

    /**
     * Speichert eine Ausgabe und aktualisiert das zugehörige Budget.
     *
     * @param ausgabe Die zu speichernde Ausgabe.
     * @return Die gespeicherte Ausgabe.
     */
    @Override
    @Transactional
    public Ausgabe save(Ausgabe ausgabe) {
        if (ausgabe.getBudget() == null || ausgabe.getBudget().getId() == null) {
            throw new IllegalArgumentException("Budget ID cannot be null");
        }
        Budget budget = budgetService.findById(ausgabe.getBudget().getId())
                .orElseThrow(() -> new NotFoundException("Budget not found"));

        ausgabe.setBudget(budget);
        budget.getAusgaben().add(ausgabe);
        budgetService.updateRestBetrag(budget);
        return super.save(ausgabe);
    }

    /**
     * Löscht eine Ausgabe und aktualisiert das zugehörige Budget.
     *
     * @param ausgabeId Die ID der zu löschenden Ausgabe.
     */
    @Transactional
    public void delete(Long ausgabeId) {
        Ausgabe ausgabe = repository.findById(ausgabeId)
                .orElseThrow(() -> new NotFoundException("Ausgabe not found"));
        Budget budget = ausgabe.getBudget();
        if (budget != null) {
            budgetService.removeAusgabeFromBudget(ausgabe);
        }
        repository.delete(ausgabe);
    }

    /**
     * Holt eine Ausgabe basierend auf einem Datum zwischen zwei gegebenen Daten und der Benutzer-ID.
     *
     * @param von Das Startdatum.
     * @param bis Das Enddatum.
     * @param benutzerId Die ID des Benutzers.
     * @return Die gefundene Ausgabe.
     */
    @Transactional
    public Ausgabe holeAusgabenAktuellesDatum(LocalDate von, LocalDate bis, String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabeByDatumBetweenAndBenutzerID(von, bis, benutzerId);
    }

    /**
     * Findet eine Ausgabe anhand der Kategorie.
     *
     * @param kategorie Die Ausgabenkategorie.
     * @return Die Ausgabe der angegebenen Kategorie.
     */
    @Transactional
    public Ausgabe findByKategorie(AusgabeKategorie kategorie){
        return this.repository.findAusgabeByAusgabeKategorie(kategorie);
    }

    /**
     * Holt die Ausgaben eines Benutzers für den aktuellen Monat.
     *
     * @param benutzerId Die ID des Benutzers.
     * @return Eine Menge von Ausgaben des Benutzers für den aktuellen Monat.
     */
    @Transactional
    public Set<Ausgabe> holeAusgabenAktuellesDatum(String benutzerId) {
        return this.repository.findAusgabenByMonat(benutzerId, LocalDate.now().getMonthValue());
    }

    /**
     * Berechnet die Summe der Ausgaben eines Kontos für den aktuellen Monat.
     *
     * @param kontoId Die ID des Kontos.
     * @return Die Summe der Ausgaben des Kontos für den aktuellen Monat.
     */
    @Transactional
    public BigDecimal getSummeAusgabenDesMonat(Long kontoId) {
        Set<Ausgabe> ausgabeSet = holeAusgabenAktuellesDatum(kontoId);
        BigDecimal summeAusgaben = BigDecimal.ZERO;
        for (Ausgabe ausgabe : ausgabeSet) {
            if(ausgabe != null && ausgabe.getBetrag() != null){
                summeAusgaben  = summeAusgaben.add(ausgabe.getBetrag());
            }
        }
        return summeAusgaben;
    }

    /**
     * Berechnet die Summe der Ausgaben eines Benutzers für den aktuellen Monat.
     *
     * @param benutzerId Die ID des Benutzers.
     * @return Die Summe der Ausgaben des Benutzers für den aktuellen Monat.
     */
    @Transactional
    public BigDecimal getSummeAusgabenDesMonat(String benutzerId) {
        Set<Ausgabe> ausgabeSet = holeAusgabenAktuellesDatum(benutzerId);
        BigDecimal summeAusgaben = BigDecimal.ZERO;
        for (Ausgabe ausgabe : ausgabeSet) {
            if(ausgabe != null && ausgabe.getBetrag() != null){
                summeAusgaben  = summeAusgaben.add(ausgabe.getBetrag());
            }
        }
        return summeAusgaben;
    }

    /**
     * Holt die Ausgaben eines Kontos für den aktuellen Monat.
     *
     * @param kontoId Die ID des Kontos.
     * @return Eine Menge von Ausgaben des Kontos für den aktuellen Monat.
     */
    @Transactional
    public Set<Ausgabe> holeAusgabenAktuellesDatum(Long kontoId) {
        this.kontoClient.findById(kontoId);
        return this.repository.findAusgabenByMonat(kontoId, LocalDate.now().getMonthValue());
    }

    /**
     * Holt alle Ausgaben eines Benutzers, sortiert nach Datum absteigend.
     *
     * @param benutzerId Die ID des Benutzers.
     * @return Eine Menge von Ausgaben des Benutzers sortiert nach Datum absteigend.
     */
    @Transactional
    public Set<Ausgabe> holeAllAusgabenByDatumDesc(String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAllOrderByDatumDesc(benutzerId);
    }

    /**
     * Berechnet die Gesamtsumme aller Ausgaben eines Benutzers.
     *
     * @param benutzerId Die ID des Benutzers.
     * @return Die Gesamtsumme der Ausgaben des Benutzers.
     */
    @Transactional
    public BigDecimal getSummeAlleAusgaben(String benutzerId) {
        Set<Ausgabe> ausgabeSet = this.repository.findAllByBenutzerID(benutzerId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : ausgabeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }
        return summe;
    }

    /**
     * Berechnet die Gesamtsumme aller Ausgaben eines Kontos.
     *
     * @param kontoId Die ID des Kontos.
     * @return Die Gesamtsumme der Ausgaben des Kontos.
     */
    @Transactional
    public BigDecimal getSummeAlleAusgaben(Long kontoId) {
        Set<Ausgabe> ausgabeSet = this.repository.findAllByKontoId(kontoId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : ausgabeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }
        return summe;
    }
}
