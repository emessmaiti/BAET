package de.th.koeln.transaktionenservice.controller;

import de.th.koeln.transaktionenservice.dtos.FinanzdatenDto;
import de.th.koeln.transaktionenservice.services.TransaktionenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/transaktionen")
public class TransaktionenController {

    private final TransaktionenService service;

    @Autowired
    public TransaktionenController(TransaktionenService service) {
        this.service = service;
    }

    @GetMapping("/{kontoId}")
    public Set<FinanzdatenDto> getTransaktionen(@PathVariable Long kontoId) {
        return this.service.getAlleFinanzdaten(kontoId);
    }

}
