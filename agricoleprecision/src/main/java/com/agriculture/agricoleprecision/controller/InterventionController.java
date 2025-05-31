package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Intervention;
import com.agriculture.agricoleprecision.service.InterventionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventions")
public class InterventionController {

    private final InterventionService service;

    public InterventionController(InterventionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Intervention> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intervention> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Intervention create(@RequestBody Intervention intervention) {
        return service.save(intervention);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Intervention> update(@PathVariable Long id, @RequestBody Intervention intervention) {
        return service.update(id, intervention)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/parcelle/{parcelleId}")
    public List<Intervention> getByParcelle(@PathVariable Long parcelleId) {
        return service.findByParcelleId(parcelleId);
    }

    @GetMapping("/type/{type}")
    public List<Intervention> getByType(@PathVariable String type) {
        return service.findByType(type);
    }
}
