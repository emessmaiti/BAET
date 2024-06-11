package de.th.koeln.finanzdatenservices.dto;

public class BenutzerDatenDto {

    private Long id;
    private String email;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private String sub;

    public BenutzerDatenDto() {
    }

    public BenutzerDatenDto(Long id, String email, String vorname, String nachname, String geschlecht, String sub) {
        this.id = id;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.sub = sub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
