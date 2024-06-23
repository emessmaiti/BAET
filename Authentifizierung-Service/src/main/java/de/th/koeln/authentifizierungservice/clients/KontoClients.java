package de.th.koeln.authentifizierungservice.clients;

import de.th.koeln.authentifizierungservice.dto.KontoDaten;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kontodaten-service")
public interface KontoClients {

    @PostMapping("/api/konto")
    ResponseEntity<KontoDaten> save(@RequestBody KontoDaten konto);
}
