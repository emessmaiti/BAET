package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.entities.FinanzielleZiel;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanzielleZielService extends BaseService<FinanzielleZiel> {

    @Autowired
    protected FinanzielleZielService(BaseRepository<FinanzielleZiel> repository) {
        super(repository);
    }
}
