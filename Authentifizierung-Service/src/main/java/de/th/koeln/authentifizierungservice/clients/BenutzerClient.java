package de.th.koeln.authentifizierungservice.clients;

import de.th.koeln.authentifizierungservice.dto.BenutzerDaten;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    @PostMapping("/api/benutzer")
    void saveBenutzer(@RequestBody BenutzerDaten benutzer);

    @GetMapping("/api/benutzer/email/{email}")
    BenutzerDaten getBenutzerIfExist(@PathVariable("email") String email);

    @GetMapping("/api/benutzer/sub/{sub}")
    BenutzerDaten getBenutzerBySub(@PathVariable("sub") String sub);

    @PutMapping("/api/benutzer/lastLogin/{sub}")
    void updateLetzteAnmeldung(@PathVariable String sub);
}
