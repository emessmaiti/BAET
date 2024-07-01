package de.th.koeln.transaktionenservice.clients;

import de.th.koeln.transaktionenservice.dtos.FinanzdatenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * Feign Client zur Kommunikation mit dem Finanzdaten-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Finanzdaten-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um aktuelle Einnahmen und Ausgaben eines Kontos abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "finanzdaten-client" dient als Bezeichner für diesen Client, und die URL "http://localhost:8086/api" gibt die Basis-URL des Finanzdaten-Services an.</p>
 */
@FeignClient(name = "finanzdaten-client", url = "http://localhost:8086/api")
public interface FinanzdatenClient {

    /**
     * Holt alle aktuellen Einnahmen eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Finanzdaten-Daten-Transfer-Objekten (FinanzdatenDto) für Einnahmen.
     */
    @GetMapping("/einnahmen/all/monat/{kontoId}")
    Set<FinanzdatenDto> getAlleEinnahmenAktuellesMonats(@PathVariable Long kontoId);

    /**
     * Holt alle aktuellen Ausgaben eines Kontos anhand der Konto-ID.
     *
     * @param kontoId Die Konto-ID.
     * @return Eine Menge von Finanzdaten-Daten-Transfer-Objekten (FinanzdatenDto) für Ausgaben.
     */
    @GetMapping("/ausgaben/all/monat/{kontoId}")
    Set<FinanzdatenDto> getAlleAusgabenAktuellesMonats(@PathVariable Long kontoId);
}
