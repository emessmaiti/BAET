package de.th.koeln.authentifizierungservice.clients;

import de.th.koeln.authentifizierungservice.dto.KontoDaten;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign Client Interface zur Kommunikation mit dem kontodaten-service.
 * Dieses Interface ermöglicht die Interaktion mit dem Konto-Datenservice,
 * um Kontoinformationen zu speichern und abzurufen.
 *
 * <p>Das Interface definiert verschiedene Endpunkte, die vom Feign Client verwendet werden,
 * um HTTP-Anfragen an den kontodaten-service zu senden.</p>
 *
 * <b>@FeignClient(name = "kontodaten-client", url = "http://localhost:8087")</b>: Diese Annotation definiert den Namen des
 * externen Services, mit dem dieser Feign Client kommunizieren wird. Der Name "kontodaten-client"
 * und die Basis-URL "http://localhost:8087" müssen korrekt konfiguriert sein, um die Verbindung herzustellen.
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.</p>
 */
@FeignClient(name = "kontodaten-client", url = "http://localhost:8087")
public interface KontoClients {

    /**
     * Speichert neue Kontodaten.
     *
     * Diese Methode sendet eine POST-Anfrage an den Konto-Service, um die angegebenen Kontodaten zu speichern.
     *
     * @param konto Die zu speichernden Kontodaten.
     * @return Eine ResponseEntity, die die gespeicherten Kontodaten enthält.
     */
    @PostMapping("/api/konto")
    ResponseEntity<KontoDaten> save(@RequestBody KontoDaten konto);

    /**
     * Findet Kontodaten anhand der Benutzer-ID.
     *
     * Diese Methode sendet eine GET-Anfrage an den Konto-Service, um die Kontodaten des angegebenen Benutzers abzurufen.
     *
     * @param benutzerId Die ID des Benutzers, dessen Kontodaten abgerufen werden sollen.
     * @return Eine ResponseEntity, die die Kontodaten des angegebenen Benutzers enthält.
     */
    @GetMapping("/api/konto/benutzer/{benutzerId}")
    ResponseEntity<KontoDaten> findByBenutzerId(@PathVariable String benutzerId);
}
