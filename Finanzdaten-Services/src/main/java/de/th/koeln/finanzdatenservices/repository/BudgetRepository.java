package de.th.koeln.finanzdatenservices.repository;

import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.entities.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Das Repository-Interface für die Entität Budget.
 *
 * <p>Dieses Interface erweitert {@link BaseRepository} und bietet spezifische Abfragen für Budgets</p>
 */
public interface BudgetRepository extends BaseRepository<Budget> {

    /**
     * Findet Budgets eines Benutzers anhand der Benutzer-ID.
     *
     * @param benutzerID Die ID des Benutzers.
     * @return Eine Menge von Budgets des Benutzers.
     */
    @Query("SELECT b FROM Budget b WHERE b.benutzerID = :benutzerID")
    Set<Budget> findBudgetsByBenutzerID(@Param("benutzerID") String benutzerID);

    /**
     * Findet Budgets einer bestimmten Kategorie innerhalb eines Datumsbereichs.
     *
     * @param kategorie Die Kategorie der Budgets.
     * @param startDate Das Startdatum des Bereichs.
     * @param endDate Das Enddatum des Bereichs.
     * @return Eine Liste von Budgets der angegebenen Kategorie innerhalb des Datumsbereichs.
     */
    List<Budget> findBudgetByKategorieAndStartDatumBetween(AusgabeKategorie kategorie, LocalDate startDate, LocalDate endDate);
}
