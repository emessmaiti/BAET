package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.BenutzerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    @GetMapping("/api/benutzer/sub/{sub}")
     BenutzerDTO getBenutzerBySub(@PathVariable("sub") String sub);
}
