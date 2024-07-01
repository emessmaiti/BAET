package de.th.koeln.kontodatenservice.services;

import de.th.koeln.kontodatenservice.dtos.AusgabenDTO;
import de.th.koeln.kontodatenservice.dtos.BenutzerDTO;
import de.th.koeln.kontodatenservice.dtos.EinnahmeDTO;
import de.th.koeln.kontodatenservice.clients.AusgabenClient;
import de.th.koeln.kontodatenservice.clients.BenutzerClient;
import de.th.koeln.kontodatenservice.clients.EinnahmeClient;
import de.th.koeln.kontodatenservice.entities.Kontodaten;
import de.th.koeln.kontodatenservice.exceptions.NotFoundException;
import de.th.koeln.kontodatenservice.repositories.KontodatenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

/**
 * Service zur Verwaltung von Kontodaten.
 *
 * <p>Dieser Service bietet Methoden zur Erstellung, Aktualisierung, Löschung und Abfrage von Kontodaten.
 * Er kommuniziert mit externen Services über Feign Clients, um Benutzerdaten, Einnahmen und Ausgaben zu verwalten.</p>
 */
@Service
@Transactional
public class KontodatenService {

    private final KontodatenRepository repository;
    private final BenutzerClient benutzerClient;
    private final EinnahmeClient einnahmeClient;
    private final AusgabenClient ausgabenClient;

    /**
     * Konstruktor zur Initialisierung der Repository- und Client-Abhängigkeiten.
     *
     * @param repository Das Repository zur Verwaltung der Kontodaten.
     * @param benutzerClient Der Feign Client zur Kommunikation mit dem Benutzerdaten-Service.
     * @param einnahmeClient Der Feign Client zur Kommunikation mit dem Einnahmen-Service.
     * @param ausgabenClient Der Feign Client zur Kommunikation mit dem Ausgaben-Service.
     */
    @Autowired
    public KontodatenService(KontodatenRepository repository, BenutzerClient benutzerClient, EinnahmeClient einnahmeClient, AusgabenClient ausgabenClient) {
        this.repository = repository;
        this.benutzerClient = benutzerClient;
        this.einnahmeClient = einnahmeClient;
        this.ausgabenClient = ausgabenClient;
    }

    /**
     * Speichert Kontodaten.
     *
     * @param kontodaten Die zu speichernden Kontodaten.
     * @return Die gespeicherten Kontodaten.
     */
    public Kontodaten save(Kontodaten kontodaten) {
        return this.repository.save(kontodaten);
    }

    /**
     * Aktualisiert Kontodaten.
     *
     * @param id Die ID der zu aktualisierenden Kontodaten.
     * @param kontodaten Die aktualisierten Kontodaten.
     * @return Die aktualisierten Kontodaten.
     */
    public Kontodaten update(Long id, Kontodaten kontodaten) {
        Optional<Kontodaten> kd = repository.findById(id);
        if (kd.isPresent()) {
            Kontodaten existingKD = kd.get();
            existingKD.setKontostand(kontodaten.getKontostand());
            return repository.save(existingKD);
        } else {
            throw new RuntimeException("Kontodaten mit der ID " + id + " existiert nicht");
        }
    }

    /**
     * Löscht Kontodaten.
     *
     * @param id Die ID der zu löschenden Kontodaten.
     */
    public void delete(Long id) {
        Optional<Kontodaten> kd = findKontoById(id);
        kd.ifPresent(repository::delete);
    }

    /**
     * Findet Kontodaten anhand der ID.
     *
     * @param id Die ID der Kontodaten.
     * @return Die gefundenen Kontodaten.
     */
    public Optional<Kontodaten> findKontoById(Long id) {
        if (!this.repository.existsById(id)) {
            throw new NotFoundException("Konto nicht gefunden");
        }
        return this.repository.findById(id);
    }

    /**
     * Findet Kontodaten anhand der Benutzer-ID.
     *
     * @param benutzerID Die Benutzer-ID.
     * @return Die gefundenen Kontodaten.
     */
    public Kontodaten findKontoByBenutzerId(String benutzerID) {
        BenutzerDTO benutzerDTO = this.benutzerClient.getBenutzerBySub(benutzerID);
        if (benutzerDTO == null || benutzerDTO.getSub() == null) {
            throw new NotFoundException("Benutzer nicht gefunden");
        }

        return this.repository.findKontodatenByBenutzerId(benutzerDTO.getSub());
    }

    /**
     * Berechnet den Kontostand anhand der Benutzer-ID.
     *
     * @param benutzerID Die Benutzer-ID.
     * @return Der berechnete Kontostand.
     */
    public BigDecimal getKontoStandByBenutzerId(String benutzerID) {
        BenutzerDTO benutzerDTO = this.benutzerClient.getBenutzerBySub(benutzerID);

        if (benutzerDTO == null || benutzerDTO.getSub() == null) {
            throw new NotFoundException("Benutzer nicht gefunden");
        }

        BigDecimal einnahmenSumme = this.einnahmeClient.getEinnahmeSumme(benutzerID);
        BigDecimal ausgabenSumme = this.ausgabenClient.getAusgabenSumme(benutzerID);

        Kontodaten kontodaten = this.repository.findKontodatenByBenutzerId(benutzerID);
        if (kontodaten == null) {
            throw new NotFoundException("Konto nicht gefunden");
        }
        BigDecimal kontostand = einnahmenSumme.subtract(ausgabenSumme);
        kontodaten.setKontostand(kontostand);
        this.repository.save(kontodaten);

        return kontostand;
    }

    /**
     * Berechnet den Kontostand anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Der berechnete Kontostand.
     */
    public BigDecimal getKontoStandByKontoId(Long kontoId) {
        Optional<Kontodaten> kontoOpt = findKontoById(kontoId);

        if (kontoOpt.isEmpty()) {
            throw new NotFoundException("Konto nicht gefunden: " + kontoId);
        }
        Kontodaten kontodaten = kontoOpt.get();
        BigDecimal einnahmeSumme = this.einnahmeClient.getEinnahmeSumme(kontoId);
        BigDecimal ausgabeSumme = this.ausgabenClient.getAusgabenSumme(kontoId);
        BigDecimal kontostand = einnahmeSumme.subtract(ausgabeSumme);
        kontodaten.setKontostand(kontostand);
        this.repository.save(kontodaten);
        return kontostand;
    }

    /**
     * Holt alle Einnahmen eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Einnahmen-Daten-Transfer-Objekten (EinnahmeDTO) des Kontos.
     */
    public Set<EinnahmeDTO> findAllEinnahmenByKontoId(Long kontoId) {
        return this.einnahmeClient.findAllByKontoId(kontoId);
    }

    /**
     * Holt alle Ausgaben eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Iterable von Ausgaben-Daten-Transfer-Objekten (AusgabenDTO) des Kontos.
     */
    public Iterable<AusgabenDTO> findAllAusgabenByKontoId(Long kontoId) {
        return this.ausgabenClient.findAllByKontoId(kontoId);
    }

}
