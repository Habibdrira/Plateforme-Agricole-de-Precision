package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.repository.ParcelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcelles")
public class ParcelleRestController {

    @Autowired
    private ParcelleRepository parcelleRepository;

    @PostMapping
    public Parcelle createParcelle(@RequestBody Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    @GetMapping
    public List<Parcelle> getAllParcelles() {
        return parcelleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Parcelle getParcelleById(@PathVariable Long id) {
        return parcelleRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Parcelle updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelle) {
        Parcelle existing = parcelleRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNom(parcelle.getNom());
            existing.setUtilisateur(parcelle.getUtilisateur());
            return parcelleRepository.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteParcelle(@PathVariable Long id) {
        parcelleRepository.deleteById(id);
    }
}