package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.service.BaseService;
import de.th.koeln.finanzdatenservices.service.BudgetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/budget")
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

    @GetMapping
    public Set<Budget> getAlleBudgetsAktuellesMonat(@AuthenticationPrincipal Jwt jwt) {
        String benutzerId = jwt.getSubject();
        return this.service.getBudgetsAktuellesMonats(benutzerId);
    }

}
