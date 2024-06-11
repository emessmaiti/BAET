package de.th.koeln.kontodatenservice.dtos;

import java.time.LocalDateTime;

public class BenutzerDTO {


    private String vorname;
    private String nachname;
    private String email;
    private String geschlecht;
    private String sub;
    private LocalDateTime letzteAnmeldung;

    public BenutzerDTO() {}

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
