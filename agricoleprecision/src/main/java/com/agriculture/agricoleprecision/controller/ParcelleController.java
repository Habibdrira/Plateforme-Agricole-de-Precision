package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.repository.ParcelleRepository;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcelles")
@CrossOrigin(origins = "*") // pour autoriser les requêtes depuis un frontend
public class ParcelleController {

    @Autowired
    private ParcelleRepository parcelleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // ✅ Récupérer toutes les parcelles (pour l'admin)
    @GetMapping
    public List<Parcelle> getAllParcelles() {
        return parcelleRepository.findAll();
    }

    // ✅ Récupérer les parcelles d'un utilisateur
    @GetMapping("/user/{userId}")
    public List<Parcelle> getParcellesByUser(@PathVariable Long userId) {
        return parcelleRepository.findByUtilisateurId(userId);
    }

    // ✅ Créer une parcelle
    @PostMapping
    public Parcelle createParcelle(@RequestBody Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    // ✅ Mettre à jour une parcelle
    @PutMapping("/{id}")
    public Parcelle updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelleDetails) {
        Parcelle parcelle = parcelleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parcelle non trouvée"));

        parcelle.setNom(parcelleDetails.getNom());
        parcelle.setLocalisation(parcelleDetails.getLocalisation());
        parcelle.setSurface(parcelleDetails.getSurface());
        parcelle.setTypeSol(parcelleDetails.getTypeSol());
        parcelle.setUtilisateur(parcelleDetails.getUtilisateur());

        return parcelleRepository.save(parcelle);
    }

    // ✅ Supprimer une parcelle
    @DeleteMapping("/{id}")
    public void deleteParcelle(@PathVariable Long id) {
        parcelleRepository.deleteById(id);
    }
}
