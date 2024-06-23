package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.EinnahmeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Set;

@FeignClient(name = "einnahmen-client", url = "http://localhost:8086/api")
public interface EinnahmeClient {

    @GetMapping("/einnahmen/all/{sub}")
     Iterable<EinnahmeDTO> findAll(@PathVariable String sub);

    @GetMapping("/einnahmen/getSumme/benutzer/{benutzerId}")
     BigDecimal getEinnahmeSumme(@PathVariable String benutzerId);

    @GetMapping("/einnahmen/all/konto/{kontoId}")
    Set<EinnahmeDTO> findAllByKontoId(@PathVariable Long kontoId);

    @GetMapping("/einnahmen/getSumme/konto/{kontoId}")
    BigDecimal getEinnahmeSumme(@PathVariable Long kontoId);

    @GetMapping("/einnahmen/getSumme")
    ResponseEntity<BigDecimal> getSumme(@RequestParam(name = "benutzerId", required = false) String benutzerId,
                                        @RequestParam(name = "kontoId", required = false) Long kontoId);


}


