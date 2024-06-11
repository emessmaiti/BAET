package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.FinanzielleZiel;
import de.th.koeln.finanzdatenservices.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finanzziel")
public class FinanzielleZielController extends BaseController<FinanzielleZiel> {

    protected FinanzielleZielController(BaseService<FinanzielleZiel> service) {
        super(service);
    }
}
