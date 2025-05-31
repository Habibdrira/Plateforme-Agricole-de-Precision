package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.service.ParcelleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return parcelleService.findById(id)
                .map(parcelle -> ResponseEntity.ok().body(parcelle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Parcelle createParcelle(@RequestBody Parcelle parcelle) {
        return parcelleService.save(parcelle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parcelle> updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelle) {
        return parcelleService.update(id, parcelle)
                .map(updatedParcelle -> ResponseEntity.ok().body(updatedParcelle))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        if (parcelleService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
