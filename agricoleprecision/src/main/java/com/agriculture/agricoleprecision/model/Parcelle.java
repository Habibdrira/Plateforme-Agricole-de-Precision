package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;


@Entity
public class Parcelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String localisation;

    private double surface;

    private String typeSol;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public double getSurface() { return surface; }
    public void setSurface(double surface) { this.surface = surface; }

    public String getTypeSol() { return typeSol; }
    public void setTypeSol(String typeSol) { this.typeSol = typeSol; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}