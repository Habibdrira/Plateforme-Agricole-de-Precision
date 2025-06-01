package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "culture")
public class Culture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeCulture;

    @Column(nullable = false)
    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "parcelle_id", nullable = false)
    private Parcelle parcelle;

    public Culture() {
    }

    public Culture(String typeCulture, LocalDate datePlantation, Parcelle parcelle) {
        this.typeCulture = typeCulture;
        this.datePlantation = datePlantation;
        this.parcelle = parcelle;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTypeCulture() { return typeCulture; }
    public void setTypeCulture(String typeCulture) { this.typeCulture = typeCulture; }
    public LocalDate getDatePlantation() { return datePlantation; }
    public void setDatePlantation(LocalDate datePlantation) { this.datePlantation = datePlantation; }
    public Parcelle getParcelle() { return parcelle; }
    public void setParcelle(Parcelle parcelle) { this.parcelle = parcelle; }
}
