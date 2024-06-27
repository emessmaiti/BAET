package de.th.koeln.finanzdatenservices.repository;

import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.entities.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BudgetRepository extends BaseRepository<Budget> {


    @Query("SELECT b FROM Budget b WHERE b.benutzerID = :benutzerID")
    Set<Budget> findBudgetsByBenutzerID(@Param("benutzerID") String benutzerID);

    List<Budget> findBudgetByKategorieAndStartDatumBetween(AusgabeKategorie kategorie, LocalDate startDate, LocalDate endDate);


}
