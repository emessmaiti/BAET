package de.th.koeln.finanzdatenservices.service;


import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.Budget;
import de.th.koeln.finanzdatenservices.exceptions.NotFoundException;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import de.th.koeln.finanzdatenservices.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BudgetService extends BaseService<Budget> {


    protected BudgetRepository repository;

    @Autowired
    protected BudgetService(BaseRepository<Budget> repository) {
        super(repository);
        this.repository = (BudgetRepository) repository;
    }

    @Override
    @Transactional
    public Budget save(Budget budget) {
        if (budget.getKontoId() == null) {
            throw new IllegalArgumentException("kontoId cannot be null");
        }

        budget.setRestBetrag(budget.getBetrag());
        budget.setProgress(BigDecimal.ZERO);
        return super.save(budget);
    }

    @Transactional
    public Budget addAusgabeToBudget(Ausgabe ausgabe) {
        Budget budget = repository.findById(ausgabe.getBudget().getId())
                .orElseThrow(() -> new NotFoundException("Budget not found"));
        budget.getAusgaben().add(ausgabe);
        ausgabe.setBudget(budget);
        updateRestBetrag(budget);
        budget.setProgress(calculateProgress(budget));
        return repository.save(budget);
    }

    @Transactional
    public Budget removeAusgabeFromBudget(Ausgabe ausgabe) {
        Budget budget = repository.findById(ausgabe.getBudget().getId())
                .orElseThrow(() -> new NotFoundException("Budget not found"));
        budget.getAusgaben().remove(ausgabe);
        ausgabe.setBudget(null);
        updateRestBetrag(budget);
        budget.setProgress(calculateProgress(budget));
        return repository.save(budget);
    }

    public void updateRestBetrag(Budget budget) {
        BigDecimal totalAusgaben = budget.getAusgaben().stream()
                .map(Ausgabe::getBetrag)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        budget.setRestBetrag(budget.getBetrag().subtract(totalAusgaben));
        budget.setProgress(calculateProgress(budget));
    }

    private BigDecimal calculateProgress(Budget budget) {
        BigDecimal spentAmount = budget.getBetrag().subtract(budget.getRestBetrag());
        if (budget.getBetrag().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return spentAmount.divide(budget.getBetrag(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

    public Set<Budget> getBudgetsAktuellesMonats(String benutzerId) {
        Set<Budget> budgets = this.repository.findBudgetsByBenutzerID(benutzerId);
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
}