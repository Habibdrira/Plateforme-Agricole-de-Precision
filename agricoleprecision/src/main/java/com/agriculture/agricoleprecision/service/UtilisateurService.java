package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
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
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> update(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setUsername(utilisateurDetails.getUsername());
            utilisateur.setPassword(utilisateurDetails.getPassword());
            utilisateur.setRole(utilisateurDetails.getRole());
            return utilisateurRepository.save(utilisateur);
        });
    }

    public boolean delete(Long id) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateurRepository.delete(utilisateur);
            return true;
        }).orElse(false);
    }
}
