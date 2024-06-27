package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.client.KontoClient;
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



    protected EinnahmeRepository repository;
    protected KontoClient kontoClient;

    @Autowired
    protected EinnahmeService(BaseRepository<Einnahme> repository, KontoClient kontoClient) {
        super(repository);
        this.repository = (EinnahmeRepository) repository;
        this.kontoClient = kontoClient;
    }

    public Set<Einnahme> holeEinnahmenBeiDatum(String benutzerId, int monat) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findEinnahmeByMonth(benutzerId, monat);
    }

    public Set<Einnahme> holeEinnahmenAktuellesDatum(String benutzerId) {
        return this.repository.findEinnahmeByMonth(benutzerId, LocalDate.now().getMonthValue());
    }

    public Set<Einnahme> holeEinnahmenAktuellesDatum(Long kontoId) {
        this.kontoClient.findById(kontoId);
        return this.repository.findEinnahmeByMonth(kontoId, LocalDate.now().getMonthValue());
    }

    public BigDecimal getSummeEinnahmenDesMonat(String benutzerId) {
        Set<Einnahme> einnahmeSet = holeEinnahmenAktuellesDatum(benutzerId);
        BigDecimal summeEinnahmen = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            if(einnahme != null && einnahme.getBetrag() != null){
                summeEinnahmen  = summeEinnahmen.add(einnahme.getBetrag());
            }
        }
        return summeEinnahmen;
    }

    public BigDecimal getSummeEinnahmenDesMonat(Long kontoId) {
        Set<Einnahme> einnahmeSet = holeEinnahmenAktuellesDatum(kontoId);
        BigDecimal summeEinnahmen = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            if(einnahme != null && einnahme.getBetrag() != null){
                summeEinnahmen  = summeEinnahmen.add(einnahme.getBetrag());
            }
        }
        return summeEinnahmen;
    }

    public Set<Einnahme> holleAlleEinnahmeDesc(String id) {
        findByBenutzerId(id);
        return this.repository.findAllOrderByDatumDesc(id);
    }

    public Einnahme findByKategorie(EinnahmeKategorie kategorie) {
       return this.repository.findEinnahmeByEinnahmeKategorie(kategorie);
    }

    public BigDecimal getSummeAlleEinnahmen(String benutzerId) {
        Set<Einnahme> einnahmeSet = this.repository.findAllByBenutzerID(benutzerId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            summe = summe.add(einnahme.getBetrag());
        }

        return summe;
    }

    public BigDecimal getSummeAlleEinnahmen(Long kontoId) {
        Set<Einnahme> einnahmeSet = this.repository.findAllByKontoId(kontoId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Einnahme einnahme : einnahmeSet) {
            summe = summe.add(einnahme.getBetrag());
        }

        return summe;
    }
}
