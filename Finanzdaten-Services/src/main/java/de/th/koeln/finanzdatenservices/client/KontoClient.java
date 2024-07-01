package de.th.koeln.finanzdatenservices.client;

import de.th.koeln.finanzdatenservices.dto.KontoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Feign Client zur Kommunikation mit dem Konto-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Konto-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Kontoinformationen abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "Konto-Client" dient als Bezeichner für diesen Client,
 * und die URL "http://localhost:8087/api" gibt die Basis-URL des Konto-Services an.</p>
 */
@FeignClient(name = "Konto-Client", url = "http://localhost:8087/api")
public interface KontoClient {

    /**
     * Ruft Kontoinformationen anhand der Konto-ID ab.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Konto-Service, um ein Konto anhand der angegebenen Konto-ID abzurufen.
     * Die Antwort wird als Optional<KontoDTO> zurückgegeben, um anzuzeigen, ob das Konto gefunden wurde oder nicht.</p>
     *
     * @param kontoId Die ID des Kontos.
     * @return Ein Optional, das das Konto-Daten-Transfer-Objekt (KontoDTO) enthält, falls gefunden.
     */
    @GetMapping("/konto/{kontoId}")
    Optional<KontoDTO> findById(@PathVariable long kontoId);
}
