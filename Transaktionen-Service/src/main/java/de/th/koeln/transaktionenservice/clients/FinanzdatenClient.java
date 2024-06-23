package de.th.koeln.transaktionenservice.clients;

import de.th.koeln.transaktionenservice.dtos.FinanzdatenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "finanzdaten-client", url = "http://localhost:8086/api")
public interface FinanzdatenClient {

    @GetMapping("/einnahmen/all/monat/{kontoId}")
    Set<FinanzdatenDto> getAlleEinnahmenAktuellesMonats(@PathVariable Long kontoId);

    @GetMapping("/ausgaben/all/monat/{kontoId}")
    Set<FinanzdatenDto> getAlleAusgabenAktuellesMonats(@PathVariable Long kontoId);
}
