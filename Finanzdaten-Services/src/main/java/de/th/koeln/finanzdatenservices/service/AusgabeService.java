package de.th.koeln.finanzdatenservices.service;

import de.th.koeln.finanzdatenservices.client.KontoClient;
import de.th.koeln.finanzdatenservices.entities.Ausgabe;
import de.th.koeln.finanzdatenservices.entities.AusgabeKategorie;
import de.th.koeln.finanzdatenservices.entities.Einnahme;
import de.th.koeln.finanzdatenservices.repository.AusgabeRepository;
import de.th.koeln.finanzdatenservices.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
public class AusgabeService extends BaseService<Ausgabe> {


    protected AusgabeRepository repository;
    protected KontoClient kontoClient;

    @Autowired
    protected AusgabeService(BaseRepository<Ausgabe> repository, KontoClient kontoClient) {
        super(repository);
        this.repository = (AusgabeRepository) repository;
        this.kontoClient = kontoClient;
    }

    public Ausgabe holeAusgabenAktuellesDatum(LocalDate von, LocalDate bis, String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabeByDatumBetweenAndBenutzerID(von, bis, benutzerId);
    }

    public Ausgabe findByKategorie(AusgabeKategorie kategorie){
        return this.repository.findAusgabeByAusgabeKategorie(kategorie);
    }

    public Set<Ausgabe> holeAusgabenAktuellesDatum(String benutzerId, int monat) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabenByMonat(benutzerId, monat);
    }

    public Set<Ausgabe> holeAusgabenAktuellesDatum(String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAusgabenByMonat(benutzerId, LocalDate.now().getMonthValue());
    }

    public BigDecimal getSummeAusgabenDesMonat(Long kontoId) {
        Set<Ausgabe> einnahmeSet = holeAusgabenAktuellesDatum(kontoId);
        BigDecimal summeEinnahmen = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            if(ausgabe != null && ausgabe.getBetrag() != null){
                summeEinnahmen  = summeEinnahmen.add(ausgabe.getBetrag());
            }
        }
        return summeEinnahmen;
    }

    public BigDecimal getSummeAusgabenDesMonat(String benutzerId) {
        Set<Ausgabe> einnahmeSet = holeAusgabenAktuellesDatum(benutzerId);
        BigDecimal summeEinnahmen = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            if(ausgabe != null && ausgabe.getBetrag() != null){
                summeEinnahmen  = summeEinnahmen.add(ausgabe.getBetrag());
            }
        }
        return summeEinnahmen;
    }

    public Set<Ausgabe> holeAusgabenAktuellesDatum(Long kontoId) {
        this.kontoClient.findById(kontoId);
        return this.repository.findAusgabenByMonat(kontoId, LocalDate.now().getMonthValue());
    }

    public Set<Ausgabe> holeAllAusgabenByDatumDesc(String benutzerId) {
        this.repository.findByBenutzerID(benutzerId);
        return this.repository.findAllOrderByDatumDesc(benutzerId);
    }

    public BigDecimal getSummeAlleAusgaben(String benutzerId) {
        Set<Ausgabe> einnahmeSet = this.repository.findAllByBenutzerID(benutzerId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }

        return summe;
    }

    public BigDecimal getSummeAlleAusgaben(Long kontoId) {
        Set<Ausgabe> einnahmeSet = this.repository.findAllByKontoId(kontoId);
        BigDecimal summe = BigDecimal.ZERO;
        for (Ausgabe ausgabe : einnahmeSet) {
            summe = summe.add(ausgabe.getBetrag());
        }

        return summe;
    }
}
