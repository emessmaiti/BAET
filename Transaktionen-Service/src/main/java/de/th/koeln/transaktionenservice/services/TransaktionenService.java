package de.th.koeln.transaktionenservice.services;

import de.th.koeln.transaktionenservice.clients.FinanzdatenClient;
import de.th.koeln.transaktionenservice.dtos.FinanzdatenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransaktionenService {

    private final FinanzdatenClient client;

    @Autowired
    public TransaktionenService(FinanzdatenClient client) {
        this.client = client;
    }

    public Set<FinanzdatenDto> getAlleFinanzdaten(Long kontoId) {
        Set<FinanzdatenDto> einnahmen = this.client.getAlleEinnahmenAktuellesMonats(kontoId);
        Set<FinanzdatenDto> ausgaben = this.client.getAlleAusgabenAktuellesMonats(kontoId);

        return Stream.concat(einnahmen.stream(), ausgaben.stream())
                .sorted(Comparator.comparing(FinanzdatenDto::getDatum))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
