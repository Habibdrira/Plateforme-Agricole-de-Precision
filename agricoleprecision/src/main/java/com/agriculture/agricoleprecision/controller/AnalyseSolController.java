package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.AnalyseSol;
import com.agriculture.agricoleprecision.service.AnalyseSolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyse-sols")
public class AnalyseSolController {

    private final AnalyseSolService analyseSolService;

    public AnalyseSolController(AnalyseSolService analyseSolService) {
        this.analyseSolService = analyseSolService;
    }

    @GetMapping
    public List<AnalyseSol> getAllAnalysesSol() {
        return analyseSolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyseSol> getAnalyseSolById(@PathVariable Long id) {
        return analyseSolService.findById(id)
                .map(analyseSol -> ResponseEntity.ok().body(analyseSol))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnalyseSol createAnalyseSol(@RequestBody AnalyseSol analyseSol) {
        return analyseSolService.save(analyseSol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalyseSol> updateAnalyseSol(@PathVariable Long id, @RequestBody AnalyseSol analyseSol) {
        return analyseSolService.update(id, analyseSol)
                .map(updatedAnalyseSol -> ResponseEntity.ok().body(updatedAnalyseSol))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyseSol(@PathVariable Long id) {
        if (analyseSolService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
