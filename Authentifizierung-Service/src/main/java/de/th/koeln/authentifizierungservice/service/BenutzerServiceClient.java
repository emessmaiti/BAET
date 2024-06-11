package de.th.koeln.authentifizierungservice.service;

import de.th.koeln.authentifizierungservice.dto.BenutzerDaten;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "benutzerdaten-service")
public interface BenutzerServiceClient {

    @PostMapping("/benutzer")
    void saveBenutzer(@RequestBody BenutzerDaten benutzer);

    @GetMapping("/benutzer/email/{email}")
    BenutzerDaten getBenutzerIfExist(@PathVariable("email") String email);

    @GetMapping("/benutzer/sub/{sub}")
    BenutzerDaten getBenutzerBySub(@PathVariable("sub") String sub);

    @PutMapping("/benutzer/lastLogin/{sub}")
    void updateLetzteAnmeldung(@PathVariable String sub);
}
