package de.th.koeln.finanzdatenservices.repository;

import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.entities.EinnahmeKategorie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface EinnahmeRepository extends BaseRepository<Einnahme> {

    @Query("SELECT e FROM Einnahme e WHERE e.benutzerID = :benutzerID AND MONTH(e.datum) = :monat ORDER BY e.datum DESC ")
    Set<Einnahme> findEinnahmeByMonth(@Param("benutzerID") String benutzerID, @Param("monat") int monat);

    @Query("SELECT e FROM Einnahme e WHERE e.benutzerID = :benutzerID ORDER BY e.datum DESC ")
    Set<Einnahme> findAllOrderByDatumDesc(@Param("benutzerID") String benutzerID);

    Einnahme findEinnahmeByEinnahmeKategorie(EinnahmeKategorie kategorie);

}
