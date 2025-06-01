package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcelle")
public class Parcelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private double surface;

    @Column(nullable = false)
    private String typeSol;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "parcelle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Culture> cultures = new ArrayList<>();

    public Parcelle() {
    }

    public Parcelle(String nom, String localisation, double surface, String typeSol, Utilisateur utilisateur) {
        this.nom = nom;
        this.localisation = localisation;
        this.surface = surface;
        this.typeSol = typeSol;
        this.utilisateur = utilisateur;
    }

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
    public List<Culture> getCultures() { return cultures; }
    public void setCultures(List<Culture> cultures) { this.cultures = cultures; }
}
