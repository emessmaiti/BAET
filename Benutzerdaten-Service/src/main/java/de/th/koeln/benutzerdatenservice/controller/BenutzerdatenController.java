package de.th.koeln.benutzerdatenservice.controller;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;
import de.th.koeln.benutzerdatenservice.services.IBenutzerdatenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benutzer")
public class BenutzerdatenController {

    @Autowired
    IBenutzerdatenImpl service;

    @PostMapping
    public Benutzerdaten createBenutzer(@RequestBody Benutzerdaten benutzerdaten) {
        return service.speichern(benutzerdaten);
    }

    @PutMapping("/{id}")
    public Benutzerdaten updateBenutzer(@PathVariable("id") long id, @RequestBody Benutzerdaten benutzer) {
        benutzer.setId(id);
        return service.updateBenutzer(benutzer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBenutzer(@PathVariable("id") long Id) {
        service.loeschen(Id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/email/{email}")
    public Benutzerdaten getBenutzerByEmail(@PathVariable("email") String email) {
        return service.findBenutzerByEmail(email);
    }

    @GetMapping("/sub/{sub}")
    public Benutzerdaten getBenutzerBySub(@PathVariable("sub") String sub) {
        return service.findBenutzerBySub(sub);
    }

    @GetMapping("/id/{id}")
    public Benutzerdaten getBenutzerById(@PathVariable("id") long id) {
        return service.findBenutzerById(id);
    }

    @PutMapping("/lastLogin/{sub}")
    public void updateLetzteAnmeldung(@PathVariable String sub) {
        service.updateLastLogin(sub);
    }


}
