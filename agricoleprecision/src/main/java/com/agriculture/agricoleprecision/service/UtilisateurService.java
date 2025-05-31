package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;  // Ajout du password encoder

    // Injection du repository + initialisation de l’encodeur
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        // Encoder le mot de passe avant sauvegarde
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> update(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setUsername(utilisateurDetails.getUsername());

            // Encoder le mot de passe seulement si un nouveau est fourni
            if (utilisateurDetails.getPassword() != null && !utilisateurDetails.getPassword().isEmpty()) {
                utilisateur.setPassword(passwordEncoder.encode(utilisateurDetails.getPassword()));
            }

            utilisateur.setRole(utilisateurDetails.getRole());
            return utilisateurRepository.save(utilisateur);
        });
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public boolean delete(Long id) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateurRepository.delete(utilisateur);
            return true;
        }).orElse(false);
    }
}
