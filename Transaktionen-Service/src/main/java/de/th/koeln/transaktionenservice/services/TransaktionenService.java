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

/**
 * Service zur Verwaltung von Transaktionen.
 *
 * <p>Dieser Service bietet Methoden zum Abrufen und Kombinieren von Finanzdaten (Einnahmen und Ausgaben)
 * aus verschiedenen Quellen über einen Feign Client.</p>
 */
@Service
public class TransaktionenService {

    private final FinanzdatenClient client;

    /**
     * Konstruktor zur Initialisierung des Finanzdaten-Clients.
     *
     * @param client Der Feign Client zur Kommunikation mit dem Finanzdaten-Service.
     */
    @Autowired
    public TransaktionenService(FinanzdatenClient client) {
        this.client = client;
    }

    /**
     * Holt alle Finanzdaten (Einnahmen und Ausgaben) für ein gegebenes Konto.
     *
     * @param kontoId Die ID des Kontos.
     * @return Ein Set von Finanzdaten-Daten-Transfer-Objekten (FinanzdatenDto), sortiert nach Datum.
     */
    public Set<FinanzdatenDto> getAlleFinanzdaten(Long kontoId) {
        Set<FinanzdatenDto> einnahmen = this.client.getAlleEinnahmenAktuellesMonats(kontoId);
        Set<FinanzdatenDto> ausgaben = this.client.getAlleAusgabenAktuellesMonats(kontoId);

        return Stream.concat(einnahmen.stream(), ausgaben.stream())
                .sorted(Comparator.comparing(FinanzdatenDto::getDatum))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
