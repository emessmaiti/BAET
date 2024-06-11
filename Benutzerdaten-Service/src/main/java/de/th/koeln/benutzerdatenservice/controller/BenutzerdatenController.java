package de.th.koeln.benutzerdatenservice.controller;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;
import de.th.koeln.benutzerdatenservice.services.IBenutzerdatenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/benutzer")
public class BenutzerdatenController {

    @Autowired
    IBenutzerdatenImpl benutzerSrevice;

    @PostMapping
    public Benutzerdaten createBenutzer(@RequestBody Benutzerdaten benutzerdaten) {
        return benutzerSrevice.speichern(benutzerdaten);
    }

    @PutMapping("/{id}")
    public Benutzerdaten updateBenutzer(@PathVariable("id") long id, @RequestBody Benutzerdaten benutzer) {
        benutzer.setId(id);
        return benutzerSrevice.updateBenutzer(benutzer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBenutzer(@PathVariable("id") long Id) {
        benutzerSrevice.loeschen(Id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public String home() {
        return "Hallo Service AUTH";
    }

    @GetMapping("/email/{email}")
    public Benutzerdaten getBenutzerByEmail(@PathVariable("email") String email) {
        return benutzerSrevice.findBenutzerByEmail(email);
    }

    @GetMapping("/sub/{sub}")
    public Benutzerdaten getBenutzerBySub(@PathVariable("sub") String sub) {
        return benutzerSrevice.findBenutzerBySub(sub);
    }

    @GetMapping("/id/{id}")
    public Benutzerdaten getBenutzerById(@PathVariable("id") long id) {
        return benutzerSrevice.findBenutzerById(id);
    }

    @PutMapping("/lastLogin/{sub}")
    public void updateLetzteAnmeldung(@PathVariable String sub) {
        benutzerSrevice.updateLastLogin(sub);
    }


}
