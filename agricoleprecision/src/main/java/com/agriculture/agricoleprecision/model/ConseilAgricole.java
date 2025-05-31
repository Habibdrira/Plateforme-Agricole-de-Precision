package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conseil_agricole")
public class ConseilAgricole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String saison;  // ex: "printemps", "été", "automne", "hiver"

    private String cultureAssociee;  // ex: "maïs", "blé", etc.

    @Column(length = 2000)
    private String description;

    // Getters et setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSaison() {
        return saison;
    }
    public void setSaison(String saison) {
        this.saison = saison;
    }
    public String getCultureAssociee() {
        return cultureAssociee;
    }
    public void setCultureAssociee(String cultureAssociee) {
        this.cultureAssociee = cultureAssociee;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
