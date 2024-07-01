package de.th.koeln.finanzdatenservices.client;

import de.th.koeln.finanzdatenservices.dto.BenutzerDatenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client zur Kommunikation mit dem Benutzerdaten-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Benutzerdaten-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Benutzerdaten abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "benutzerdaten-service" dient als Bezeichner für diesen Client und muss mit dem Namen des externen
 * Dienstes übereinstimmen, wie er in der Service-Discovery registriert ist.</p>
 */
@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    /**
     * Ruft Benutzerdaten anhand der Benutzer-ID ab.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Benutzerdaten-Service, um Benutzerdaten anhand der angegebenen Benutzer-ID abzurufen.
     * Die Antwort wird als {@link BenutzerDatenDto} zurückgegeben.</p>
     *
     * @param id Die ID des Benutzers.
     * @return Das Benutzer-Daten-Transfer-Objekt (BenutzerDatenDto) mit den Benutzerdaten.
     */
    @GetMapping("/api/id/{id}")
    BenutzerDatenDto getBenutzerById(@PathVariable("id") long id);
}
