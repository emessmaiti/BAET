package de.th.koeln.finanzdatenservices.service;


import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

@Service
public class BudgetService extends BaseService<Budget> {

    protected final BaseRepository<Budget> repository;

    @Autowired
    protected BudgetService(BaseRepository<Budget> repository, BaseRepository<Budget> repository1) {
        super(repository);
        this.repository = repository1;
    }


    private BigDecimal calculateRestBetrag(Budget budget) {
        Set<Ausgabe> ausgaben = budget.getAusgaben();
        BigDecimal totalAusgaben = BigDecimal.ZERO;
        YearMonth aktuelleMonat = YearMonth.now();

        for (Ausgabe ausgabe : ausgaben) {
            YearMonth budgetMonat = YearMonth.from(ausgabe.getDatum());
            if (ausgabe.getAusgabeKategorie().equals(budget.getKategorie())
                    && budgetMonat.equals(aktuelleMonat)) {
                totalAusgaben = totalAusgaben.add(ausgabe.getBetrag());
            }
        }
        BigDecimal restBetrag = budget.getBudget().subtract(totalAusgaben);
        budget.setRestBetrag(restBetrag);

        return restBetrag;
    }



    public BigDecimal getTotalAusgabenByBudget(Long budgetId) {
        Budget budget = this.repository.findById(budgetId).orElseThrow(() -> new NotFoundException("Budget mit der ID " + budgetId + "nicht gefunden"));
        Set<Ausgabe> ausgaben = budget.getAusgaben();
        BigDecimal totalAusgaben = BigDecimal.ZERO;

        for (Ausgabe ausgabe : ausgaben) {
            if (ausgabe.getAusgabeKategorie().equals(budget.getKategorie())) {
                totalAusgaben = totalAusgaben.add(ausgabe.getBetrag());
            }
        }

        return totalAusgaben;
    }

    public Set<Budget> getBudgetsAktuellesMonats(String benutzerId) {
        Set<Budget> budgets = this.repository.findAllByBenutzerID(benutzerId);
        Set<Budget> budgetsAktuellesMonats = new HashSet<>();
        YearMonth aktuelleMonat = YearMonth.now();

        for (Budget budget : budgets) {
            YearMonth budgetMonat = YearMonth.from(budget.getStartDatum());
            if (budgetMonat.equals(aktuelleMonat)) {
                budget.setProgress(calculateProgress(budget));
                budgetsAktuellesMonats.add(budget);
            }
        }
        return budgetsAktuellesMonats;
    }

    private BigDecimal calculateProgress(Budget budget) {
        BigDecimal totalAusgaben = getTotalAusgabenByBudget(budget.getId());
        BigDecimal budgetAmount = budget.getBudget();
        if (budgetAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return totalAusgaben.divide(budgetAmount, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }
}
