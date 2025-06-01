package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.service.ParcelleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcelles")
public class ParcelleController {

    private final ParcelleService parcelleService;

    public ParcelleController(ParcelleService parcelleService) {
        this.parcelleService = parcelleService;
    }

    @GetMapping
    public List<Parcelle> getAllParcelles() {
        return parcelleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelle> getParcelleById(@PathVariable Long id) {
        Optional<Parcelle> parcelle = parcelleService.findById(id);
        return parcelle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Parcelle> getParcellesByUtilisateur(@PathVariable Long utilisateurId) {
        return parcelleService.findByUtilisateurId(utilisateurId);
    }

    @PostMapping
    public Parcelle createParcelle(@RequestBody Parcelle parcelle) {
        return parcelleService.save(parcelle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcelle> updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelleDetails) {
        Optional<Parcelle> updated = parcelleService.update(id, parcelleDetails);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        boolean deleted = parcelleService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
