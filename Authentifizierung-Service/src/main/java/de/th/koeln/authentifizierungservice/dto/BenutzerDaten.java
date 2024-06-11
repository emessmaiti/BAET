package de.th.koeln.authentifizierungservice.dto;

import java.time.LocalDateTime;

public class BenutzerDaten {

    private Long id;
    private String email;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private String sub;
    private LocalDateTime letzteAnmeldung;

    public BenutzerDaten() {
    }

    public BenutzerDaten(String vorname, String nachname, String email, String geschlecht, String sub, LocalDateTime letzteAnmeldung) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.geschlecht = geschlecht;
        this.sub = sub;
        this.letzteAnmeldung = letzteAnmeldung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public LocalDateTime getLetzteAnmeldung() {
        return letzteAnmeldung;
    }

    public void setLetzteAnmeldung(LocalDateTime letzteAnmeldung) {
        this.letzteAnmeldung = letzteAnmeldung;
    }
}
