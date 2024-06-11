package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.repository.AusgabeRepository;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class AusgabeService extends BaseService<Ausgabe> {

    @Autowired
    protected AusgabeRepository repository;

    protected AusgabeService(BaseRepository<Ausgabe> repository) {
        super(repository);
    }

    public Ausgabe holeAusgabenBeiDatum(LocalDate von, LocalDate bis, String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabeByDatumBetweenAndBenutzerID(von, bis, benutzerId);
    }

    public Ausgabe findByKategorie(AusgabeKategorie kategorie){
        return this.repository.findAusgabeByAusgabeKategorie(kategorie);
    }

    public Set<Ausgabe> holeAusgabenBeiDatum(String benutzerId, int monat) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabenByMonat(benutzerId, monat);
    }

    public Set<Ausgabe> holeAllAusgabenByDatumDesc(String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAllOrderByDatumDesc(benutzerId);
    }

    public BigDecimal getSumme(String benutzerId) {
        Set<Ausgabe> einnahmeSet = this.repository.findAllByBenutzerID(benutzerId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }

        return summe;
    }

    public BigDecimal getSumme(Long kontoId) {
        Set<Ausgabe> einnahmeSet = this.repository.findAllByKontoId(kontoId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }

        return summe;
    }
}
