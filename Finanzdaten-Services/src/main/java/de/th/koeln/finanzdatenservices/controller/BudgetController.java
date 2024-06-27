package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.service.BaseService;
import de.th.koeln.finanzdatenservices.service.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/budget")
public class BudgetController extends BaseController<Budget> {

    @Autowired
    protected BudgetService service;

    protected BudgetController(BaseService<Budget> baseService) {
        super(baseService);
    }

    @GetMapping("/{benutzerId}")
    public Set<Budget> getAlleBudgetsAktuellesMonat(@PathVariable String benutzerId) {
        return this.service.getBudgetsAktuellesMonats(benutzerId);
    }

    @PostMapping("/{budgetId}/ausgaben")
    public ResponseEntity<Budget> addAusgabeToBudget(@PathVariable Long budgetId, @RequestBody Ausgabe ausgabe) {
        Budget budget = service.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget not found"));
        ausgabe.setBudget(budget);
        Budget updatedBudget = service.addAusgabeToBudget(ausgabe);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{budgetId}/ausgaben/{ausgabeId}")
    public ResponseEntity<Budget> removeAusgabeFromBudget(@PathVariable Long budgetId, @PathVariable Long ausgabeId) {
        Budget budget = service.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget not found"));
        Ausgabe ausgabe = budget.getAusgaben().stream().filter(a -> a.getId().equals(ausgabeId)).findFirst().orElseThrow(() -> new NotFoundException("Ausgabe not found"));
        Budget updatedBudget = service.removeAusgabeFromBudget(ausgabe);
        return ResponseEntity.ok(updatedBudget);
    }

    @GetMapping
    public ResponseEntity<Set<Budget>> getBudgetsAktuellesMonats(@RequestParam String benutzerId) {
        Set<Budget> budgets = service.getBudgetsAktuellesMonats(benutzerId);
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long budgetId) {
        Budget budget = service.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget not found"));
        return ResponseEntity.ok(budget);
    }


}
