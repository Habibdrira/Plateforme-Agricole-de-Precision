package com.agriculture.agricoleprecision.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcelle> parcelles;

    public enum Role {
        ADMIN,
        AGRICULTEUR
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Parcelle> getParcelles() { return parcelles; }
    public void setParcelles(List<Parcelle> parcelles) { this.parcelles = parcelles; }
}
