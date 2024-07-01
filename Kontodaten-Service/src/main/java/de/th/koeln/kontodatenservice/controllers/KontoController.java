package de.th.koeln.kontodatenservice.controllers;

import de.th.koeln.kontodatenservice.dtos.AusgabenDTO;
import de.th.koeln.kontodatenservice.dtos.EinnahmeDTO;
import de.th.koeln.kontodatenservice.entities.Kontodaten;
import de.th.koeln.kontodatenservice.services.KontodatenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * REST-Controller zur Verwaltung von Kontodaten.
 *
 * <p>Dieser Controller bietet Endpunkte zur Verwaltung von Kontodaten, einschließlich der Erstellung,
 * Aktualisierung, Löschung und Abfrage von Kontodaten sowie der Abfrage von Einnahmen und Ausgaben.</p>
 */
@RestController
@RequestMapping("/api/konto")
public class KontoController {

    private final KontodatenService service;

    /**
     * Konstruktor zur Initialisierung des Kontodaten-Services.
     *
     * @param service Der Kontodaten-Service.
     */
    @Autowired
    public KontoController(KontodatenService service) {
        this.service = service;
    }

    /**
     * Speichert Kontodaten.
     *
     * @param konto Die zu speichernden Kontodaten.
     * @return Die gespeicherten Kontodaten als ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Kontodaten> save(@RequestBody Kontodaten konto) {
        Kontodaten savedKonto = service.save(konto);
        return ResponseEntity.ok(savedKonto);
    }

    /**
     * Aktualisiert Kontodaten.
     *
     * @param kontoId Die ID der zu aktualisierenden Kontodaten.
     * @param konto Die aktualisierten Kontodaten.
     * @return Die aktualisierten Kontodaten als ResponseEntity.
     */
    @PutMapping("/{kontoId}")
    public ResponseEntity<Kontodaten> update(@PathVariable long kontoId, @RequestBody Kontodaten konto) {
        Kontodaten updatedKonto = service.update(kontoId, konto);
        return ResponseEntity.ok(updatedKonto);
    }

    /**
     * Löscht Kontodaten.
     *
     * @param kontoId Die ID der zu löschenden Kontodaten.
     * @return Eine leere ResponseEntity.
     */
    @DeleteMapping("/{kontoId}")
    public ResponseEntity<Void> delete(@PathVariable long kontoId) {
        service.delete(kontoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Findet Kontodaten anhand der ID.
     *
     * @param kontoId Die ID der Kontodaten.
     * @return Die gefundenen Kontodaten als ResponseEntity.
     */
    @GetMapping("/{kontoId}")
    public ResponseEntity<Kontodaten> findById(@PathVariable long kontoId) {
        Optional<Kontodaten> konto = service.findKontoById(kontoId);
        return konto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Findet Kontodaten anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Die gefundenen Kontodaten als ResponseEntity.
     */
    @GetMapping("/benutzer/{benutzerId}")
    public ResponseEntity<Kontodaten> findByBenutzerId(@PathVariable String benutzerId) {
        Kontodaten konto = service.findKontoByBenutzerId(benutzerId);
        return ResponseEntity.ok(konto);
    }

    /**
     * Berechnet den Kontostand anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Der berechnete Kontostand als ResponseEntity.
     */
    @GetMapping("/kontostand/benutzer/{benutzerId}")
    public ResponseEntity<BigDecimal> getKontostandByBenutzerId(@PathVariable String benutzerId) {
        BigDecimal kontostand = service.getKontoStandByBenutzerId(benutzerId);
        return ResponseEntity.ok(kontostand);
    }

    /**
     * Berechnet den Kontostand anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Der berechnete Kontostand als ResponseEntity.
     */
    @GetMapping("/kontostand/{kontoId}")
    public ResponseEntity<BigDecimal> getKontostandByKontoId(@PathVariable Long kontoId) {
        BigDecimal kontostand = service.getKontoStandByKontoId(kontoId);
        return ResponseEntity.ok(kontostand);
    }

    /**
     * Berechnet den Kontostand anhand der Benutzer-ID oder der Konto-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @param kontoId Die Konto-ID.
     * @return Der berechnete Kontostand als ResponseEntity.
     */
    @GetMapping("/kontostand")
    public ResponseEntity<BigDecimal> getKontostand(@RequestParam(name = "benutzerId", required = false) String benutzerId,
                                                    @RequestParam(name = "kontoId", required = false) Long kontoId) {

        if (benutzerId != null && kontoId != null) {
            return ResponseEntity.badRequest().body(null);
        }

        BigDecimal summe = BigDecimal.ZERO;
        if (kontoId != null) {
            summe = this.service.getKontoStandByKontoId(kontoId);
        } else if (benutzerId != null) {
            summe = this.service.getKontoStandByBenutzerId(benutzerId);
        }
        return ResponseEntity.ok(summe);
    }

    /**
     * Holt alle Einnahmen eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Iterable von Einnahme-Daten-Transfer-Objekten (EinnahmeDTO) des Kontos als ResponseEntity.
     */
    @GetMapping("/einnahmen/{kontoId}")
    public ResponseEntity<Iterable<EinnahmeDTO>> getEinnahmen(@PathVariable Long kontoId) {
        Iterable<EinnahmeDTO> einnahmen = service.findAllEinnahmenByKontoId(kontoId);
        return ResponseEntity.ok(einnahmen);
    }

    /**
     * Holt alle Ausgaben eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Iterable von Ausgaben-Daten-Transfer-Objekten (AusgabenDTO) des Kontos als ResponseEntity.
     */
    @GetMapping("/ausgaben/{kontoId}")
    public ResponseEntity<Iterable<AusgabenDTO>> getAusgaben(@PathVariable Long kontoId) {
        Iterable<AusgabenDTO> ausgaben = service.findAllAusgabenByKontoId(kontoId);
        return ResponseEntity.ok(ausgaben);
    }
}
