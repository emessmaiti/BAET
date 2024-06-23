package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.service.BaseService;
import de.th.koeln.finanzdatenservices.service.BudgetService;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/budget")
public class BudgetController extends BaseController<Budget> {

    protected BudgetService service;

    protected BudgetController(BaseService<Budget> baseService, BudgetService service) {
        super(baseService);
        this.service = service;
    }

    @GetMapping("/{benutzerId}")
    public Set<Budget> getAlleBudgetsAktuellesMonat(@PathVariable String benutzerId) {
        return this.service.getBudgetsAktuellesMonats(benutzerId);
    }

//    @GetMapping
//    public Set<Budget> getAlleBudgetsAktuellesMonat(Authentication authentication) {
//
//        String benutzerId = authentication.getName();
//        return this.service.getBudgetsAktuellesMonats(benutzerId);
//    }

}
