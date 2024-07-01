package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.FinanzielleZiel;
import de.th.koeln.finanzdatenservices.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Controller zur Verwaltung von finanziellen Zielen.
 *
 * <p>Dieser Controller erweitert {@link BaseController} und bietet Endpunkte zur Verwaltung von finanziellen Zielen.</p>
 */
@RestController
@RequestMapping("/api/ziel")
public class FinanzielleZielController extends BaseController<FinanzielleZiel> {

    /**
     * Konstruktor zur Initialisierung des BaseService.
     *
     * @param service Der Service zur Verwaltung der finanziellen Ziele.
     */
    protected FinanzielleZielController(BaseService<FinanzielleZiel> service) {
        super(service);
    }
}
