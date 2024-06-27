package de.th.koeln.authentifizierungservice.clients;

import de.th.koeln.authentifizierungservice.dto.BenutzerDaten;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign Client Interface zur Kommunikation mit dem benutzerdaten-service.
 * Dieses Interface ermöglicht die Interaktion mit dem Benutzer-Datenservice,
 * um Benutzerinformationen zu speichern, abzurufen und zu aktualisieren.
 *
 * <p>Das Interface definiert verschiedene Endpunkte, die vom Feign Client verwendet werden,
 * um HTTP-Anfragen an den benutzerdaten-service zu senden.</p>
 *
 *  <b>@FeignClient(name = "benutzerdaten-service")</b>: Diese Annotation definiert den Namen des
 * externen Services, mit dem dieser Feign Client kommunizieren wird. Der Name "benutzerdaten-service"
 * muss der gleiche sein, unter dem der Service in der Service Discovery registriert ist.
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 */
@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    /**
     * Speichert die Benutzerdaten.
     *
     * <p>Diese Methode sendet eine POST-Anfrage an den Endpunkt "/api/benutzer",
     * um die bereitgestellten Benutzerdaten zu speichern.</p>
     *
     * @param benutzer Ein {@link BenutzerDaten} Objekt, das die zu speichernden Benutzerdaten enthält.
     */
    @PostMapping("/api/benutzer")
    void saveBenutzer(@RequestBody BenutzerDaten benutzer);

    /**
     * Ruft Benutzerdaten anhand der E-Mail-Adresse ab, falls vorhanden.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Endpunkt "/api/benutzer/email/{email}",
     * um die Benutzerdaten für die angegebene E-Mail-Adresse abzurufen.</p>
     *
     * @param email Die E-Mail-Adresse des Benutzers.
     * @return Ein {@link BenutzerDaten} Objekt, das die Benutzerdaten enthält, falls vorhanden.
     */
    @GetMapping("/api/benutzer/email/{email}")
    BenutzerDaten getBenutzerIfExist(@PathVariable("email") String email);

    /**
     * Ruft Benutzerdaten anhand der Sub-Id (Google subject) ab.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Endpunkt "/api/benutzer/sub/{sub}",
     * um die Benutzerdaten für die angegebene Sub-Id abzurufen.</p>
     *
     * @param sub Die Sub-Id des Benutzers.
     * @return Ein {@link BenutzerDaten} Objekt, das die Benutzerdaten enthält.
     */
    @GetMapping("/api/benutzer/sub/{sub}")
    BenutzerDaten getBenutzerBySub(@PathVariable("sub") String sub);

    /**
     * Aktualisiert das letzte Anmeldedatum des Benutzers anhand der Sub-Id.
     *
     * <p>Diese Methode sendet eine PUT-Anfrage an den Endpunkt "/api/benutzer/lastLogin/{sub}",
     * um das letzte Anmeldedatum des Benutzers zu aktualisieren.</p>
     *
     * @param sub Die Sub-Id des Benutzers.
     */
    @PutMapping("/api/benutzer/lastLogin/{sub}")
    void updateLetzteAnmeldung(@PathVariable String sub);
}
