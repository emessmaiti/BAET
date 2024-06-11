package de.th.koeln.kontodatenservice.repositories;

import de.th.koeln.kontodatenservice.entities.Kontodaten;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KontodatenRepository extends JpaRepository<Kontodaten, Long> {

    Kontodaten findKontodatenByBenutzerId(String benutzer);

}
