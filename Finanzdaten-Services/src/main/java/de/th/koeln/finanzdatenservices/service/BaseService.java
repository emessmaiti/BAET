package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.client.KontoClient;
import de.th.koeln.finanzdatenservices.dto.KontoDTO;
import de.th.koeln.finanzdatenservices.entities.AbstraktEntitaet;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public abstract class BaseService<T extends AbstraktEntitaet> {

    protected final BaseRepository<T> repository;

    @Autowired
    protected KontoClient kontoClient;

    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity) {
        Optional<KontoDTO> kontoDTO = this.kontoClient.findById(entity.getKontoId());
        if (kontoDTO.isEmpty()) {
            throw new NotFoundException("Konto not found");
        }
        return repository.save(entity);
    }

    public Optional<T> findByID(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Eintrag mit der ID " + id + " wurde nicht gefunden.");
        }
        return repository.findById(id);
    }

    public String findBenutzer(String benutzer) {
        if (!this.existsBenutzer(benutzer)) {
            return "Benutzer mit der ID " + benutzer + " wurde nicht gefunden.";
        }

        return this.findBenutzer(benutzer);
    }

    public boolean existsBenutzer(String benutzer) {
        return this.repository.existsBenutzer(benutzer);
    }


    public Iterable<T> findAllByBenutzerId(String benutzerId) {
        return repository.findAllByBenutzerID(benutzerId);
    }

    public Optional<T> findByBenutzerId(String benutzerId) {
        if (!this.repository.existsByBenutzerID(benutzerId)) {
            throw new NotFoundException("Benutzer mit der ID " + benutzerId + " nicht gefunden.");
        }
        return repository.findByBenutzerID(benutzerId);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Eintrag mit der ID " + id + " wurde nicht gefunden.");
        }
        repository.deleteById(id);
    }

    public Set<T> findAllByKontoId(Long kontoId) {
        Optional<KontoDTO> kontoDTO = this.kontoClient.findById(kontoId);
        if (kontoDTO.isPresent()) {
            return this.repository.findAllByKontoId(kontoId);
        } else {
            throw new NotFoundException("Konto mit der ID " + kontoId + " nicht gefunden.");
        }

    }

}
