package de.th.koeln.finanzdatenservices.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstraktEntitaet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime erstellerZeitstempel;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = true)
    private LocalDateTime bearbeiterZeitstempel;
    @Column(nullable = false)
    private String benutzerID;
    @Column(nullable = false)
    private Long kontoId;
    @Column(nullable = false)
    private BigDecimal betrag;

    protected AbstraktEntitaet() {
    }

//    //TODO Optimize the Constructor, dont need the version and erstell-bearbeiter attributes
//    protected AbstraktEntitaet(Long version, LocalDateTime erstellerZeitstempel, LocalDateTime bearbeiterZeitstempel,
//                               String benutzerID, BigDecimal betrag
//    ) {
//        this.version = version;
//        this.erstellerZeitstempel = erstellerZeitstempel;
//        this.bearbeiterZeitstempel = bearbeiterZeitstempel;
//        this.benutzerID = benutzerID;
//        this.betrag = betrag;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBenutzerID() {
        return this.benutzerID;
    }

    public void setBenutzerID(String benutzerID) {
        this.benutzerID = benutzerID;
    }

    public Long getKontoId() {
        return kontoId;
    }

    public void setKontoId(Long kontoId) {
        this.kontoId = kontoId;
    }

    public BigDecimal getBetrag() {
        return this.betrag;
    }

    public void setBetrag(BigDecimal beitrag) {
        this.betrag = beitrag;
    }

    @PrePersist
    public void revesioniere() {
        this.erstellerZeitstempel = LocalDateTime.now();
        this.bearbeiterZeitstempel = LocalDateTime.now();
    }

    @PreUpdate
    public void setBearbeiterZeitstempel() {
        this.bearbeiterZeitstempel = LocalDateTime.now();
    }


}
