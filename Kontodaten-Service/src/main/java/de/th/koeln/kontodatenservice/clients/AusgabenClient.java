package de.th.koeln.kontodatenservice.clients;

import de.th.koeln.kontodatenservice.dtos.AusgabenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Set;

@FeignClient(name = "ausgaben-client", url = "http://localhost:8086")
public interface AusgabenClient {

    @GetMapping("/ausgaben/all/{sub}")
    Iterable<AusgabenDTO> findAll(@PathVariable String sub);

    @GetMapping("/ausgaben/getSumme/{benutzerId}")
    BigDecimal getAusgabenSumme(@PathVariable String benutzerId);

    @GetMapping("/ausgaben/all/konto/{kontoId}")
    Set<AusgabenDTO> findAllByKontoId(@PathVariable Long kontoId);

    @GetMapping("/ausgaben/getSumme/konto/{kontoId}")
    BigDecimal getAusgabenSumme(@PathVariable Long kontoId);
}
