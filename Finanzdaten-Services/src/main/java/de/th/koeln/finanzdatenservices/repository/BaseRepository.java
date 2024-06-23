package de.th.koeln.finanzdatenservices.repository;

import de.th.koeln.finanzdatenservices.entities.AbstraktEntitaet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<T extends AbstraktEntitaet> extends JpaRepository<T, Long> {

    @Query("SELECT t FROM #{#entityName} t WHERE t.benutzerID = :benutzerID")
    Set<T> findEntityByBenutzerID(@Param("benutzerID") String benutzerID);

    @Query("SELECT DISTINCT t.benutzerID FROM #{#entityName} t WHERE t.benutzerID = :benutzerID ")
    String findBenutzer(@Param("benutzerID") String benutzerID);

    @Query("SELECT COUNT (t.benutzerID) > 0 FROM #{#entityName} t WHERE t.benutzerID = :benutzerID ")
    boolean existsBenutzer(@Param("benutzerID") String benutzerID);

    Set<T> findAllByBenutzerID(String benutzerID);

    Optional<T> findByBenutzerID(String benutzerID);

    boolean existsByBenutzerID(String benutzerID);

    Set<T> findAllByKontoId(Long kontoId);


}
