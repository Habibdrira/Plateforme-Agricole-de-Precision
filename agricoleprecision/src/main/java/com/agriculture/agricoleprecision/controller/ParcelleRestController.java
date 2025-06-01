package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.ParcelleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcelles")
public class ParcelleRestController {

    @Autowired
    private ParcelleService parcelleService;

    @GetMapping
    public ResponseEntity<List<Parcelle>> getAllParcelles(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (user.getRole() == Role.ADMIN) {
            return ResponseEntity.ok(parcelleService.findAll());
        } else if (user.getRole() == Role.AGRICULTEUR) {
            return ResponseEntity.ok(parcelleService.findByUtilisateurId(user.getId()));
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelle> getParcelleById(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Parcelle parcelle = parcelleService.findById(id).orElse(null);
        if (parcelle == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.ADMIN || parcelle.getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.ok(parcelle);
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping
    public ResponseEntity<Parcelle> createParcelle(@RequestParam String nom, @RequestParam String localisation,
                                                   @RequestParam double surface, @RequestParam String typeSol,
                                                   @RequestParam Long utilisateurId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !user.getId().equals(utilisateurId)) {
            return ResponseEntity.status(403).build();
        }
        Parcelle parcelle = parcelleService.createParcelle(nom, localisation, surface, typeSol, utilisateurId);
        return ResponseEntity.ok(parcelle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcelle> updateParcelle(@PathVariable Long id, @RequestParam String nom,
                                                   @RequestParam String localisation, @RequestParam double surface,
                                                   @RequestParam String typeSol, @RequestParam Long utilisateurId,
                                                   HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Parcelle parcelle = parcelleService.findById(id).orElse(null);
        if (parcelle == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        Parcelle updated = parcelleService.updateParcelle(id, nom, localisation, surface, typeSol, utilisateurId);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Parcelle parcelle = parcelleService.findById(id).orElse(null);
        if (parcelle == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        parcelleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
