package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.EinnahmeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Set;

@FeignClient(name = "einnahmen-client", url = "http://localhost:8086")
public interface EinnahmeClient {

    @GetMapping("/einnahmen/all/{sub}")
     Iterable<EinnahmeDTO> findAll(@PathVariable String sub);

    @GetMapping("/einnahmen/getSumme/{benutzerId}")
     BigDecimal getEinnahmeSumme(@PathVariable String benutzerId);

    @GetMapping("/einnahmen/all/konto/{kontoId}")
    Set<EinnahmeDTO> findAllByKontoId(@PathVariable Long kontoId);

    @GetMapping("/einnahmen/getSumme/konto/{kontoId}")
    BigDecimal getEinnahmeSumme(@PathVariable Long kontoId);


}


