package de.th.koeln.kontodatenservice.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Kontodaten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String benutzerId;
    @Version
    private Long version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime erstellerZeitstempel;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = true)
    private LocalDateTime bearbeiterZeitstempel;
    private BigDecimal kontostand = BigDecimal.ZERO;


    public Kontodaten() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getKontostand() {
        return kontostand;
    }

    public void setKontostand(BigDecimal kontostand) {
        this.kontostand = kontostand;
    }

    public String getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(String benutzerId) {
        this.benutzerId = benutzerId;
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
