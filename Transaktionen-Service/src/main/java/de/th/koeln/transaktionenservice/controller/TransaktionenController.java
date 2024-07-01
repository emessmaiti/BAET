package de.th.koeln.transaktionenservice.controller;

import de.th.koeln.transaktionenservice.dtos.FinanzdatenDto;
import de.th.koeln.transaktionenservice.services.TransaktionenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST-Controller zur Verwaltung von Transaktionen.
 *
 * <p>Dieser Controller bietet Endpunkte zur Abfrage von Finanzdaten (Einnahmen und Ausgaben) eines Kontos.</p>
 */
@RestController
@RequestMapping("/api/transaktionen")
public class TransaktionenController {

    private final TransaktionenService service;

    /**
     * Konstruktor zur Initialisierung des Transaktionen-Services.
     *
     * @param service Der Transaktionen-Service.
     */
    @Autowired
    public TransaktionenController(TransaktionenService service) {
        this.service = service;
    }

    /**
     * Holt alle Transaktionen (Einnahmen und Ausgaben) für ein gegebenes Konto.
     *
     * @param kontoId Die ID des Kontos.
     * @return Eine Menge von Finanzdaten-Daten-Transfer-Objekten (FinanzdatenDto).
     */
    @GetMapping("/{kontoId}")
    public Set<FinanzdatenDto> getTransaktionen(@PathVariable Long kontoId) {
        return this.service.getAlleFinanzdaten(kontoId);
    }
}
