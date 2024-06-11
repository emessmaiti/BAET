package de.th.koeln.finanzdatenservices.repository;

import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface AusgabeRepository extends BaseRepository<Ausgabe> {

    Ausgabe findAusgabeByDatumBetweenAndBenutzerID(LocalDate from, LocalDate to, String benutzerID);

    @Query("SELECT a FROM Ausgabe a WHERE a.benutzerID = :benutzerID ORDER BY a.datum DESC ")
    Set<Ausgabe> findAllOrderByDatumDesc(@Param("benutzerID") String benutzerID);

    @Query("SELECT e FROM Einnahme e WHERE e.benutzerID = :benutzerID AND MONTH(e.datum) = :monat ORDER BY e.datum DESC ")
    Set<Ausgabe> findAusgabenByMonat(@Param("benutzerID") String benutzerID, @Param("monat") int monat);

    Ausgabe findAusgabeByAusgabeKategorie(AusgabeKategorie kategorie);
}
