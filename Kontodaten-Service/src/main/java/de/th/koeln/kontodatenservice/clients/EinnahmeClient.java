package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.EinnahmeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Feign Client zur Kommunikation mit dem Einnahmen-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Einnahmen-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Einnahmeninformationen abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "einnahmen-client" dient als Bezeichner für diesen Client, und die URL "http://localhost:8086/api" gibt die Basis-URL des Einnahmen-Services an.</p>
 */
@FeignClient(name = "einnahmen-client", url = "http://localhost:8086/api")
public interface EinnahmeClient {

    /**
     * Holt alle Einnahmen eines Benutzers anhand der Benutzer-ID.
     *
     * @param sub Die Benutzer-ID.
     * @return Eine Iterable von Einnahme-Daten-Transfer-Objekten (EinnahmeDTO).
     */
    @GetMapping("/einnahmen/all/{sub}")
    Iterable<EinnahmeDTO> findAll(@PathVariable String sub);

    /**
     * Berechnet die Summe der Einnahmen eines Benutzers anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Die Summe der Einnahmen als BigDecimal.
     */
    @GetMapping("/einnahmen/getSumme/benutzer/{benutzerId}")
    BigDecimal getEinnahmeSumme(@PathVariable String benutzerId);

    /**
     * Holt alle Einnahmen eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Einnahme-Daten-Transfer-Objekten (EinnahmeDTO).
     */
    @GetMapping("/einnahmen/all/konto/{kontoId}")
    Set<EinnahmeDTO> findAllByKontoId(@PathVariable Long kontoId);

    /**
     * Berechnet die Summe der Einnahmen eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Die Summe der Einnahmen als BigDecimal.
     */
    @GetMapping("/einnahmen/getSumme/konto/{kontoId}")
    BigDecimal getEinnahmeSumme(@PathVariable Long kontoId);

    /**
     * Berechnet die Summe der Einnahmen eines Benutzers oder eines Kontos.
     *
     * @param benutzerId Die Benutzer-ID (optional).
     * @param kontoId Die Konto-ID (optional).
     * @return Die Summe der Einnahmen als ResponseEntity mit BigDecimal.
     */
    @GetMapping("/einnahmen/getSumme")
    ResponseEntity<BigDecimal> getSumme(@RequestParam(name = "benutzerId", required = false) String benutzerId,
                                        @RequestParam(name = "kontoId", required = false) Long kontoId);
}
