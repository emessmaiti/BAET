package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.entities.EinnahmeKategorie;
import de.th.koeln.finanzdatenservices.service.BaseService;
import de.th.koeln.finanzdatenservices.service.EinnahmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/einnahmen")
public class EinnahmeController extends BaseController<Einnahme> {

    @Autowired
    protected EinnahmeService service;

    protected EinnahmeController(BaseService<Einnahme> baseService) {
        super(baseService);
    }

    @GetMapping("/all/desc/{id}")
    public Set<Einnahme> getAlleEinnahmenDesc(@PathVariable String id) {
        return this.service.holleAlleEinnahmeDesc(id);
    }

    @GetMapping("/all/monat/{benutzerId}/{monat}")
    public Set<Einnahme> getAlleEinnahmenByMonat(@PathVariable String benutzerId, @PathVariable int monat) {
        return this.service.holeEinnahmenBeiDatum(benutzerId, monat);
    }

    @GetMapping("/all/monat/{benutzerId}")
    public Set<Einnahme> getAlleEinnahmenByMonat(@PathVariable String benutzerId) {
        return this.service.holeEinnahmenAktuellesDatum(benutzerId);
    }

    @GetMapping("/getSumme")
    public ResponseEntity<BigDecimal> getSumme(@RequestParam(name = "benutzerId", required = false) String benutzerId,
                                               @RequestParam(name = "kontoId", required = false) Long kontoId) {

        if (benutzerId != null && kontoId != null) {
            return ResponseEntity.badRequest().body(null);
        }

        BigDecimal summe = BigDecimal.ZERO;
        if (kontoId != null) {
            summe = this.service.getSumme(kontoId);
        } else if (benutzerId != null) {
            summe = this.service.getSumme(benutzerId);
        }
        return ResponseEntity.ok(summe);
    }

    @GetMapping("/kategorie")
    public Einnahme getKategorie(@RequestParam EinnahmeKategorie kategorie) {
        return this.service.findByKategorie(kategorie);
    }

}
