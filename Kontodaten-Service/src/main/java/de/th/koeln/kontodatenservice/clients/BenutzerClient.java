package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.BenutzerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client zur Kommunikation mit dem Benutzerdaten-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Benutzerdaten-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Benutzerdaten anhand der Sub-ID abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "benutzerdaten-service" dient als Bezeichner für diesen Client und muss mit dem Namen des externen
 * Dienstes übereinstimmen, wie er in der Service-Discovery registriert ist.</p>
 */
@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    /**
     * Ruft Benutzerdaten anhand der Sub-ID ab.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Benutzerdaten-Service, um Benutzerdaten basierend auf der
     * gegebenen Sub-ID abzurufen.</p>
     *
     * @param sub Die Sub-ID des Benutzers.
     * @return Das Benutzer-Daten-Transfer-Objekt (BenutzerDTO) mit den Benutzerdaten.
     */
    @GetMapping("/api/benutzer/sub/{sub}")
    BenutzerDTO getBenutzerBySub(@PathVariable("sub") String sub);
}
