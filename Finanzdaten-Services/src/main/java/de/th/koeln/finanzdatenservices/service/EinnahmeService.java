package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.entities.EinnahmeKategorie;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import de.th.koeln.finanzdatenservices.repository.EinnahmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class EinnahmeService extends BaseService<Einnahme> {


    @Autowired
    protected EinnahmeRepository repository;

    protected EinnahmeService(BaseRepository<Einnahme> repository) {
        super(repository);
    }

    public Set<Einnahme> holeEinnahmenBeiDatum(String benutzerId, int monat) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findEinnahmeByMonth(benutzerId, monat);
    }

    public Set<Einnahme> holeEinnahmenAktuellesDatum(String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findEinnahmeByMonth(benutzerId, LocalDate.now().getMonthValue());
    }

    public Set<Einnahme> holleAlleEinnahmeDesc(String id) {
        findByBenutzerId(id);
        return this.repository.findAllOrderByDatumDesc(id);
    }

    public Einnahme findByKategorie(EinnahmeKategorie kategorie) {
       return this.repository.findEinnahmeByEinnahmeKategorie(kategorie);
    }

    public BigDecimal getSumme(String benutzerId) {
        Set<Einnahme> einnahmeSet = this.repository.findAllByBenutzerID(benutzerId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            summe = summe.add(einnahme.getBetrag());
        }

        return summe;
    }

    public BigDecimal getSumme(Long kontoId) {
        Set<Einnahme> einnahmeSet = this.repository.findAllByKontoId(kontoId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            summe = summe.add(einnahme.getBetrag());
        }

        return summe;
    }
}
