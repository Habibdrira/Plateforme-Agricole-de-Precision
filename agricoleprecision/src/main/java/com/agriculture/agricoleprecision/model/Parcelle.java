package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parcelle")
public class Parcelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private double superficie;  // en hectares par ex.

    // Relation vers l’utilisateur propriétaire de la parcelle
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Une parcelle peut avoir plusieurs cultures
    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.ALL)
    private Set<Culture> cultures;

    // Une parcelle peut avoir plusieurs analyses de sol
    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.ALL)
    private Set<AnalyseSol> analysesSol;

    // Une parcelle peut avoir plusieurs interventions
    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.ALL)
    private Set<Intervention> interventions;

    // Getters et setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getSuperficie() {
        return superficie;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Set<Culture> getCultures() {
        return cultures;
    }
    public void setCultures(Set<Culture> cultures) {
        this.cultures = cultures;
    }
    public Set<AnalyseSol> getAnalysesSol() {
        return analysesSol;
    }
    public void setAnalysesSol(Set<AnalyseSol> analysesSol) {
        this.analysesSol = analysesSol;
    }
    public Set<Intervention> getInterventions() {
        return interventions;
    }
    public void setInterventions(Set<Intervention> interventions) {
        this.interventions = interventions;
    }
}
