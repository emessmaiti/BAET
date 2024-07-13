package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.client.KontoClient;
import de.th.koeln.finanzdatenservices.dto.KontoDTO;
import de.th.koeln.finanzdatenservices.entities.AbstraktEntitaet;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

/**
 * Die abstrakte Basisklasse für Service-Klassen, die Entitäten verwalten, die von {@link AbstraktEntitaet} erben.
 * <p>Diese Klasse bietet allgemeine CRUD-Operationen und Methoden.
 * @param <T> Der Entitätstyp, der von AbstraktEntitaet erbt.
 */
public abstract class BaseService<T extends AbstraktEntitaet> {

    protected final BaseRepository<T> repository;

    @Autowired
    protected KontoClient kontoClient;

    /**
     * Konstruktor zur Initialisierung des Repositories.
     *
     * @param repository Das Repository zur Verwaltung der Entität.
     */
    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Speichert eine Entität, wenn das zugehörige Konto existiert.
     *
     * @param entity Die zu speichernde Entität.
     * @return Die gespeicherte Entität.
     */
    @Transactional
    public T save(T entity) {
        Optional<KontoDTO> kontoDTO = this.kontoClient.findById(entity.getKontoId());
        if (kontoDTO.isEmpty()) {
            throw new NotFoundException("Konto not found");
        }
        return repository.save(entity);
    }

    /**
     * Findet eine Entität anhand der ID.
     *
     * @param id Die ID der zu findenden Entität.
     * @return Eine optionale Entität.
     */
    public Optional<T> findById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Eintrag mit der ID " + id + " wurde nicht gefunden.");
        }
        return repository.findById(id);
    }

    /**
     * Findet die Benutzer-ID.
     *
     * @param benutzer Die Benutzer-ID.
     * @return Eine Nachricht, wenn der Benutzer nicht gefunden wird.
     */
    public String findBenutzer(String benutzer) {
        if (!this.existsBenutzer(benutzer)) {
            return "Benutzer mit der ID " + benutzer + " wurde nicht gefunden.";
        }
        return this.findBenutzer(benutzer);
    }

    /**
     * Überprüft, ob ein Benutzer existiert.
     *
     * @param benutzer Die Benutzer-ID.
     * @return true, wenn der Benutzer existiert, ansonsten false.
     */
    public boolean existsBenutzer(String benutzer) {
        return this.repository.existsBenutzer(benutzer);
    }

    /**
     * Findet alle Entitäten eines Benutzers anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Eine Iterable von Entitäten.
     */
    public Iterable<T> findAllByBenutzerId(String benutzerId) {
        return repository.findAllByBenutzerID(benutzerId);
    }

    /**
     * Findet eine Entität anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Eine optionale Entität.
     */
    public Optional<T> findByBenutzerId(String benutzerId) {
        if (!this.repository.existsByBenutzerID(benutzerId)) {
            throw new NotFoundException("Benutzer mit der ID " + benutzerId + " nicht gefunden.");
        }
        return repository.findByBenutzerID(benutzerId);
    }

    /**
     * Löscht eine Entität anhand der ID.
     *
     * @param id Die ID der zu löschenden Entität.
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Eintrag mit der ID " + id + " wurde nicht gefunden.");
        }
        repository.deleteById(id);
    }

    /**
     * Findet alle Entitäten eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Entitäten des Kontos.
     */
    public Set<T> findAllByKontoId(Long kontoId) {
        Optional<KontoDTO> kontoDTO = this.kontoClient.findById(kontoId);
        if (kontoDTO.isPresent()) {
            return this.repository.findAllByKontoId(kontoId);
        } else {
            throw new NotFoundException("Konto mit der ID " + kontoId + " nicht gefunden.");
        }
    }

    public T findByKontoId( Long kontoId){
        Optional<T> entity = this.repository.findById(kontoId);
        if (entity.isPresent()) {
            return entity.get();
        }
        throw new NotFoundException("Konto mit der ID " + kontoId + " nicht gefunden.");
    }
}
