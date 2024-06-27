package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.service.AusgabeService;
import de.th.koeln.finanzdatenservices.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/ausgaben")
public class AusgabeController extends BaseController<Ausgabe> {

    @Autowired
    protected AusgabeService service;

    protected AusgabeController(BaseService<Ausgabe> baseService) {
        super(baseService);
    }

    @GetMapping("/getSumme")
    public ResponseEntity<BigDecimal> getSumme(@AuthenticationPrincipal Jwt jwt){
        String benutzerID = jwt.getSubject();
        BigDecimal summe = this.service.getSummeAlleAusgaben(benutzerID);
        return ResponseEntity.ok(summe);

    }


    @GetMapping("all/monat/{benutzerID}/desc")
    public Set<Ausgabe> getAlleAusgabenByMonatDesc(@PathVariable String benutzerID) {
        return this.service.holeAllAusgabenByDatumDesc(benutzerID);
    }

    @GetMapping("/all")
    public Set<Ausgabe> getAlleAusgabenAktuellesMonats(@AuthenticationPrincipal Jwt jwt) {
        String benutzerId = jwt.getSubject();
        return this.service.holeAusgabenAktuellesDatum(benutzerId);
    }

    @GetMapping("/all/monat/{kontoId}")
    public Set<Ausgabe> getAlleAusgabenAktuellesMonats(@PathVariable Long kontoId) {
        return this.service.holeAusgabenAktuellesDatum(kontoId);
    }

    @GetMapping("/kategorie")
    public Ausgabe getKategorie(@RequestParam AusgabeKategorie kategorie) {
        return this.service.findByKategorie(kategorie);
    }

    @GetMapping("/getSumme/benutzer/{benutzerId}")
    BigDecimal getAusgabenSumme(@PathVariable String benutzerId){
        return this.service.getSummeAusgabenDesMonat(benutzerId);
    }

    @GetMapping("/getSumme/konto/{kontoId}")
    BigDecimal getAusgabenSumme(@PathVariable Long kontoId){
        return this.service.getSummeAusgabenDesMonat(kontoId);
    }
}
