package de.th.koeln.finanzdatenservices.client;

import de.th.koeln.finanzdatenservices.dto.BenutzerDatenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "benutzerdaten-service")
public interface BenutzerClient {

    @GetMapping("/api/id/{id}")
    BenutzerDatenDto getBenutzerById(@PathVariable("id") long id);


}
