package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.AusgabenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Feign Client zur Kommunikation mit dem Ausgaben-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Ausgaben-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Ausgabeninformationen abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "ausgaben-client" dient als Bezeichner für diesen Client, und die URL "http://localhost:8086/api" gibt die Basis-URL des Ausgaben-Services an.</p>
 */
@FeignClient(name = "ausgaben-client", url = "http://localhost:8086/api")
public interface AusgabenClient {

    /**
     * Holt alle Ausgaben eines Benutzers anhand der Benutzer-ID.
     *
     * @param sub Die Benutzer-ID.
     * @return Eine Iterable von Ausgaben-Daten-Transfer-Objekten (AusgabenDTO).
     */
    @GetMapping("/ausgaben/all/{sub}")
    Iterable<AusgabenDTO> findAll(@PathVariable String sub);

    /**
     * Berechnet die Summe der Ausgaben eines Benutzers anhand der Benutzer-ID.
     *
     * @param benutzerId Die Benutzer-ID.
     * @return Die Summe der Ausgaben als BigDecimal.
     */
    @GetMapping("/ausgaben/getSumme/benutzer/{benutzerId}")
    BigDecimal getAusgabenSumme(@PathVariable String benutzerId);

    /**
     * Holt alle Ausgaben eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Ausgaben-Daten-Transfer-Objekten (AusgabenDTO).
     */
    @GetMapping("/ausgaben/all/konto/{kontoId}")
    Set<AusgabenDTO> findAllByKontoId(@PathVariable Long kontoId);

    /**
     * Berechnet die Summe der Ausgaben eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Die Summe der Ausgaben als BigDecimal.
     */
    @GetMapping("/ausgaben/getSumme/konto/{kontoId}")
    BigDecimal getAusgabenSumme(@PathVariable Long kontoId);
}
