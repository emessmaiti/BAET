package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.entities.EinnahmeKategorie;
import de.th.koeln.finanzdatenservices.service.BaseService;
import de.th.koeln.finanzdatenservices.service.EinnahmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/einnahmen")
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
    public Set<Einnahme> getAlleEinnahmenAktuellesMonats(@PathVariable String benutzerId, @PathVariable int monat) {
        return this.service.holeEinnahmenBeiDatum(benutzerId, monat);
    }

    @GetMapping("/all")
    public Set<Einnahme> getAlleEinnahmenAktuellesMonats(@AuthenticationPrincipal Jwt jwt) {
        String benutzerId = jwt.getSubject();
        return this.service.holeEinnahmenAktuellesDatum(benutzerId);
    }

    @GetMapping("/all/monat/{kontoId}")
    public Set<Einnahme> getAlleEinnahmenAktuellesMonats(@PathVariable Long kontoId) {
        return this.service.holeEinnahmenAktuellesDatum(kontoId);
    }

//    @GetMapping("/getSumme")
//    public ResponseEntity<BigDecimal> getSumme(@RequestParam(name = "benutzerId", required = false) String benutzerId,
//                                               @RequestParam(name = "kontoId", required = false) Long kontoId) {
//
//        if (benutzerId != null && kontoId != null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        BigDecimal summe = BigDecimal.ZERO;
//        if (kontoId != null) {
//            summe = this.service.getSummeAlleEinnahmen(kontoId);
//        } else if (benutzerId != null) {
//            summe = this.service.getSummeAlleEinnahmen(benutzerId);
//        }
//        return ResponseEntity.ok(summe);
//    }

    @GetMapping("/getSumme")
    public ResponseEntity<BigDecimal> getSumme(@AuthenticationPrincipal Jwt jwt){
        BigDecimal summe = BigDecimal.ZERO;
        String benutzerID = jwt.getSubject();
        summe = this.service.getSummeEinnahmenDesMonat(benutzerID);
        return ResponseEntity.ok(summe);

    }

    @GetMapping("/kategorie")
    public Einnahme getKategorie(@RequestParam EinnahmeKategorie kategorie) {
        return this.service.findByKategorie(kategorie);
    }

    @GetMapping("/getSumme/benutzer/{benutzerId}")
    BigDecimal getEinnahmeSumme(@PathVariable String benutzerId){
        return this.service.getSummeEinnahmenDesMonat(benutzerId);
    }

    @GetMapping("/getSumme/konto/{kontoId}")
    BigDecimal getEinnahmeSumme(@PathVariable Long kontoId){
        return this.service.getSummeEinnahmenDesMonat(kontoId);
    }


}
