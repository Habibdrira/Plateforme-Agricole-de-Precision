 package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.ParcelleRepository;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelleService {

    @Autowired
    private ParcelleRepository parcelleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Parcelle> findAll() {
        return parcelleRepository.findAll();
    }

    public Optional<Parcelle> findById(Long id) {
        return parcelleRepository.findById(id);
    }

    public List<Parcelle> findByUtilisateurId(Long utilisateurId) {
        return parcelleRepository.findByUtilisateurId(utilisateurId);
    }

    public Parcelle createParcelle(String nom, String localisation, double surface, String typeSol, Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
        Parcelle parcelle = new Parcelle(nom, localisation, surface, typeSol, utilisateur);
        return parcelleRepository.save(parcelle);
    }

    public Parcelle updateParcelle(Long id, String nom, String localisation, double surface, String typeSol, Long utilisateurId) {
        Optional<Parcelle> existing = parcelleRepository.findById(id);
        if (existing.isPresent()) {
            Parcelle parcelle = existing.get();
            parcelle.setNom(nom);
            parcelle.setLocalisation(localisation);
            parcelle.setSurface(surface);
            parcelle.setTypeSol(typeSol);
            Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow();
            parcelle.setUtilisateur(utilisateur);
            return parcelleRepository.save(parcelle);
        }
        return null;
    }

    public void deleteById(Long id) {
        parcelleRepository.deleteById(id);
    }
}
