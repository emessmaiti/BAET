package de.th.koeln.benutzerdatenservice.services;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;
import de.th.koeln.benutzerdatenservice.exception.BenutzerdatenExceptionHandler;
import de.th.koeln.benutzerdatenservice.repository.IBenutzerdatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementierung der Benutzerdaten-Services.
 *
 * <p>Diese Klasse bietet die Implementierung der im {@link IBenutzerdatenServices} Interface definierten Methoden zur Verwaltung der Benutzerdaten.</p>
 */
@Service
@Transactional
public class IBenutzerdatenImpl implements IBenutzerdatenServices {

    @Autowired
    IBenutzerdatenRepository benutzerRepository;

    @Override
    public Benutzerdaten speichern(Benutzerdaten benutzer) {
        return benutzerRepository.save(benutzer);
    }

    @Override
    public void loeschen(long id) {
        findBenutzerById(id);
        benutzerRepository.deleteById(id);
    }

    @Override
    public Benutzerdaten findBenutzerByEmail(String email) {
        Optional<Benutzerdaten> benutzer = Optional.ofNullable(benutzerRepository.findByEmail(email));
        return benutzer.orElseThrow(() -> new BenutzerdatenExceptionHandler("Benutzer mit der Id " + email + " existiert nicht."));

    }

    @Override
    public Benutzerdaten findBenutzerById(Long Id) {
        Optional<Benutzerdaten> benutzer = benutzerRepository.findById(Id);
        return benutzer.orElseThrow(() -> new BenutzerdatenExceptionHandler("Benutzer mit der Id " + Id + " existiert nicht."));
    }

    @Override
    public Benutzerdaten updateBenutzer(Benutzerdaten benutzer) {
        Benutzerdaten exestingBenutzer = findBenutzerById(benutzer.getId());
        exestingBenutzer.setVorname(benutzer.getVorname());
        exestingBenutzer.setNachname(benutzer.getNachname());
        exestingBenutzer.setEmail(benutzer.getEmail());
        exestingBenutzer.setGeschlecht(benutzer.getGeschlecht());

        return benutzerRepository.save(exestingBenutzer);

    }

    @Override
    public Benutzerdaten findBenutzerBySub(String sub) {
        return benutzerRepository.findBenutzerdatenBySub(sub);
    }

    @Override
    public void updateLastLogin(String sub) {
        benutzerRepository.updateLastLogin(sub, LocalDateTime.now());
    }
}
