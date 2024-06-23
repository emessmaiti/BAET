package de.th.koeln.finanzdatenservices.client;

import de.th.koeln.finanzdatenservices.dto.KontoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "Konto-Client", url = "http://localhost:8087/api")
public interface KontoClient {

    @GetMapping("/konto/{kontoId}")
    Optional<KontoDTO> findById(@PathVariable long kontoId);
}
