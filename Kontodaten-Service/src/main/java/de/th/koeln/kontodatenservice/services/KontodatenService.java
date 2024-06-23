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

@Service
@Transactional
public class KontodatenService {

    private final KontodatenRepository repository;
    private final BenutzerClient benutzerClient;
    private final EinnahmeClient einnahmeClient;
    private final AusgabenClient ausgabenClient;

    @Autowired
    public KontodatenService(KontodatenRepository repository, BenutzerClient benutzerClient, EinnahmeClient einnahmeClient, AusgabenClient ausgabenClient) {
        this.repository = repository;
        this.benutzerClient = benutzerClient;
        this.einnahmeClient = einnahmeClient;
        this.ausgabenClient = ausgabenClient;
    }

    public Kontodaten save(Kontodaten kontodaten) {
        return this.repository.save(kontodaten);
    }

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

    public void delete(Long id) {
        Optional<Kontodaten> kd = findKontoById(id);
        kd.ifPresent(repository::delete);
    }

    public Optional<Kontodaten> findKontoById(Long id) {
        if (!this.repository.existsById(id)) {
            throw new NotFoundException("Konto nicht gefunden");
        }
        return this.repository.findById(id);
    }

    public Kontodaten findKontoByBenutzerId(String benutzerID) {
        BenutzerDTO benutzerDTO = this.benutzerClient.getBenutzerBySub(benutzerID);
        if (benutzerDTO == null || benutzerDTO.getSub() == null) {
            throw new NotFoundException("Benutzer nicht gefunden");
        }

        return this.repository.findKontodatenByBenutzerId(benutzerDTO.getSub());
    }

    public BigDecimal getKontoStandByBenutzerId( String benutzerID) {
        BenutzerDTO benutzerDTO = this.benutzerClient.getBenutzerBySub(benutzerID);

        if (benutzerDTO == null || benutzerDTO.getSub() == null) {
            throw new NotFoundException("Benutzer nicht gefunden");
        }

        BigDecimal einnahmenSumme = this.einnahmeClient.getEinnahmeSumme(benutzerID);
        BigDecimal ausgabenSumme = this.ausgabenClient.getAusgabenSumme(benutzerID);

        Kontodaten kontodaten = this.repository.findKontodatenByBenutzerId(benutzerDTO.getSub());
        if (kontodaten == null) {
            throw new NotFoundException("Konto nicht gefunden");
        }
        BigDecimal kontostand = einnahmenSumme.subtract(ausgabenSumme);
        kontodaten.setKontostand(kontostand);
        this.repository.save(kontodaten);

        return kontostand;
    }

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

    private BigDecimal getEinnahmeSumme(String benutzerId, Long kontoId) {
        if ((benutzerId == null && kontoId == null) || (benutzerId != null && kontoId != null)) {
            throw new IllegalArgumentException("Either benutzerId or kontoId must be provided, but not both.");
        }
        ResponseEntity<BigDecimal> response = this.einnahmeClient.getSumme(benutzerId,kontoId);
        return response.getBody();

    }


    public Set<EinnahmeDTO> findAllEinnahmenByKontoId(Long kontoId) {
       return this.einnahmeClient.findAllByKontoId(kontoId);
    }

    public Iterable<AusgabenDTO> findAllAusgabenByKontoId(Long kontoId) {
        return this.ausgabenClient.findAllByKontoId(kontoId);
    }
}
