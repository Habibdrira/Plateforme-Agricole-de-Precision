package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "intervention")
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateIntervention;

    private String typeIntervention;  // ex: "irrigation", "fertilisation", "désherbage"

    private String description;

    @ManyToOne
    @JoinColumn(name = "parcelle_id")
    private Parcelle parcelle;

    // Getters et setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateIntervention() {
        return dateIntervention;
    }
    public void setDateIntervention(LocalDate dateIntervention) {
        this.dateIntervention = dateIntervention;
    }
    public String getTypeIntervention() {
        return typeIntervention;
    }
    public void setTypeIntervention(String typeIntervention) {
        this.typeIntervention = typeIntervention;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Parcelle getParcelle() {
        return parcelle;
    }
    public void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }
}
