package de.th.koeln.benutzerdatenservice.repository;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IBenutzerdatenRepository extends JpaRepository<Benutzerdaten, Long> {

    Benutzerdaten findByEmail(String email);

    Benutzerdaten findBenutzerdatenBySub(String sub);

    @Modifying
    @Query("UPDATE Benutzerdaten b SET b.letzteAnmeldung = :letzteAnmeldung WHERE b.sub = :sub")
    void updateLastLogin(@Param("sub") String sub, @Param("letzteAnmeldung") LocalDateTime letzteAnmeldung);


}
