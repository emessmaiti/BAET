package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.entities.FinanzielleZiel;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Der Service für die Verwaltung von finanziellen Zielen.
 *
 * <p>Dieser Service erweitert {@link BaseService} und bietet allgemeine Methoden zur Verwaltung von finanziellen Zielen.</p>
 */
@Transactional
@Service
public class FinanzielleZielService extends BaseService<FinanzielleZiel> {

    /**
     * Konstruktor zur Initialisierung des Repositories.
     *
     * @param repository Das Repository zur Verwaltung der finanziellen Ziele.
     */
    @Autowired
    protected FinanzielleZielService(BaseRepository<FinanzielleZiel> repository) {
        super(repository);
    }
}
