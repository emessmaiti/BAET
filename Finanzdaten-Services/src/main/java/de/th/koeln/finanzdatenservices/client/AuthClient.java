package de.th.koeln.finanzdatenservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign Client zur Kommunikation mit dem Authentifizierungs-Service.
 *
 * <p>Dieses Interface verwendet Feign, um HTTP-Anfragen an den Authentifizierungs-Service zu senden.
 * Es definiert die erforderlichen Endpunkte, um Authentifizierungsinformationen abzurufen.</p>
 *
 * <p>Die Annotation {@link FeignClient} wird verwendet, um dieses Interface als Feign Client zu deklarieren.
 * Der Name "auth-service" dient als Bezeichner für diesen Client und muss mit dem Namen des externen Dienstes
 * übereinstimmen, wie er in der Service-Discovery registriert ist.</p>
 */
@FeignClient(value = "auth-service")
public interface AuthClient {

    /**
     * Ruft die Subjekt-ID (Sub) des authentifizierten Benutzers ab.
     *
     * <p>Diese Methode sendet eine GET-Anfrage an den Authentifizierungs-Service, um die Subjekt-ID des aktuell
     * authentifizierten Benutzers abzurufen.</p>
     *
     * @return Die Subjekt-ID (Sub) des authentifizierten Benutzers.
     */
    @GetMapping("/getSub")
    String getSub();
}
