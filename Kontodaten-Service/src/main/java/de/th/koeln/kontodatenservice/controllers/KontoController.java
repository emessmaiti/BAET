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

@RestController
@RequestMapping("/konto")
public class KontoController {

    private final KontodatenService service;

    @Autowired
    public KontoController(KontodatenService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Kontodaten> save(@RequestBody Kontodaten konto) {
        Kontodaten savedKonto = service.save(konto);
        return ResponseEntity.ok(savedKonto);
    }

    @PutMapping("/{kontoId}")
    public ResponseEntity<Kontodaten> update(@PathVariable long kontoId, @RequestBody Kontodaten konto) {
        Kontodaten updatedKonto = service.update(kontoId, konto);
        return ResponseEntity.ok(updatedKonto);
    }

    @DeleteMapping("/{kontoId}")
    public ResponseEntity<Void> delete(@PathVariable long kontoId) {
        service.delete(kontoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{kontoId}")
    public ResponseEntity<Kontodaten> findById(@PathVariable long kontoId) {
        Optional<Kontodaten> konto = service.findKontoById(kontoId);
        return konto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/benutzer/{benutzerId}")
    public ResponseEntity<Kontodaten> findByBenutzerId(@PathVariable String benutzerId) {
        Kontodaten konto = service.findKontoByBenutzerId(benutzerId);
        return ResponseEntity.ok(konto);
    }

    @GetMapping("/kontostand/benutzer/{benutzerId}")
    public ResponseEntity<BigDecimal> getKontostandByBenutzerId(@PathVariable String benutzerId) {
        BigDecimal kontostand = service.getKontoStandByBenutzerId(benutzerId);
        return ResponseEntity.ok(kontostand);
    }

    @GetMapping("/kontostand/{kontoId}")
    public ResponseEntity<BigDecimal> getKontostandByKontoId(@PathVariable Long kontoId) {
        BigDecimal kontostand = service.getKontoStandByKontoId(kontoId);
        return ResponseEntity.ok(kontostand);
    }

    @GetMapping("/kontostand")
    public ResponseEntity<BigDecimal> getKontostand(@RequestParam(name = "benutzerId", required = false) String benutzerId,
                                                    @RequestParam(name = "kontoId", required = false) Long kontoId) {

        if(benutzerId != null && kontoId != null) {
            return ResponseEntity.badRequest().body(null);
        }

       BigDecimal summe = BigDecimal.ZERO;
        if(kontoId != null){
            summe = this.service.getKontoStandByKontoId(kontoId);
        } else if(benutzerId != null){
            summe = this.service.getKontoStandByBenutzerId(benutzerId);
        }
        //Test
        return ResponseEntity.ok(summe);
    }

    @GetMapping("/einnahmen/{kontoId}")
    public ResponseEntity<Iterable<EinnahmeDTO>> getEinnahmen(@PathVariable Long kontoId) {
        Iterable<EinnahmeDTO> einnahmen = service.findAllEinnahmenByKontoId(kontoId);
        return ResponseEntity.ok(einnahmen);
    }

    @GetMapping("/ausgaben/{kontoId}")
    public ResponseEntity<Iterable<AusgabenDTO>> getAusgaben(@PathVariable Long kontoId) {
        Iterable<AusgabenDTO> ausgaben = service.findAllAusgabenByKontoId(kontoId);
        return ResponseEntity.ok(ausgaben);
    }
}
