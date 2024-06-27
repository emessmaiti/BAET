package de.th.koeln.kontodatenservice.repositories;

import de.th.koeln.kontodatenservice.entities.Kontodaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KontodatenRepository extends JpaRepository<Kontodaten, Long> {


    @Query("SELECT k from Kontodaten k WHERE k.benutzerId = :benutzerId")
    Kontodaten findKontodatenByBenutzerId(@Param("benutzerId") String benutzerId);

}
