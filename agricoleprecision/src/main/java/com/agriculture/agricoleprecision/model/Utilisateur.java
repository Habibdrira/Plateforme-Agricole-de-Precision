package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;  // Ex : "ADMIN", "AGRICULTEUR"

    // Relation : un utilisateur peut avoir plusieurs parcelles
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Parcelle> parcelles;

    // Getters et setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Set<Parcelle> getParcelles() {
        return parcelles;
    }
    public void setParcelles(Set<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }
}