package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.entities.EinnahmeKategorie;
import de.th.koeln.finanzdatenservices.service.AusgabeService;
import de.th.koeln.finanzdatenservices.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/ausgaben")
public class AusgabeController extends BaseController<Ausgabe> {

    @Autowired
    protected AusgabeService service;

    protected AusgabeController(BaseService<Ausgabe> baseService) {
        super(baseService);
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

    @GetMapping("all/monat/{benutzerID}/desc")
    public Set<Ausgabe> getAlleAusgabenByMonatDesc(@PathVariable String benutzerID) {
        return this.service.holeAllAusgabenByDatumDesc(benutzerID);
    }

    @GetMapping("all/monat/{benutzerId}")
    public Set<Ausgabe> getAlleAusgabenByMonat(@PathVariable String benutzerId) {
        return this.service.holeAusgabenBeiDatum(benutzerId, LocalDate.now().getMonthValue());
    }

    @GetMapping("/kategorie")
    public Ausgabe getKategorie(@RequestParam AusgabeKategorie kategorie) {
        return this.service.findByKategorie(kategorie);
    }
}
