package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "analyse_sol")
public class AnalyseSol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAnalyse;

    private double ph;

    private double humidite;

    private double temperature;

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
    public LocalDate getDateAnalyse() {
        return dateAnalyse;
    }
    public void setDateAnalyse(LocalDate dateAnalyse) {
        this.dateAnalyse = dateAnalyse;
    }
    public double getPh() {
        return ph;
    }
    public void setPh(double ph) {
        this.ph = ph;
    }
    public double getHumidite() {
        return humidite;
    }
    public void setHumidite(double humidite) {
        this.humidite = humidite;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public Parcelle getParcelle() {
        return parcelle;
    }
    public void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }
}
