package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur createUtilisateur(String username, String password, String role) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        utilisateur.setRole(Role.valueOf(role));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, String username, String password, String role) {
        Optional<Utilisateur> existing = utilisateurRepository.findById(id);
        if (existing.isPresent()) {
            Utilisateur utilisateur = existing.get();
            utilisateur.setUsername(username);
            if (password != null && !password.isEmpty()) {
                utilisateur.setPassword(password);
            }
            utilisateur.setRole(Role.valueOf(role));
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }
}
