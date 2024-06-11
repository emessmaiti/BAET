package de.th.koeln.benutzerdatenservice.services;

import de.th.koeln.benutzerdatenservice.entities.Benutzerdaten;

public interface IBenutzerdatenServices {

    Benutzerdaten speichern(Benutzerdaten benutzer);

    void loeschen(long id);

    Benutzerdaten findBenutzerByEmail(String email);

    Benutzerdaten findBenutzerById(Long Id);

    Benutzerdaten updateBenutzer(Benutzerdaten benutzer);

    Benutzerdaten findBenutzerBySub(String sub);

    void updateLastLogin(String sub);

}
