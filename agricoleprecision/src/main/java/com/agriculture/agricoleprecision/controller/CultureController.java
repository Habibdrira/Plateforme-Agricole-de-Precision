package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.service.CultureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultures")
public class CultureController {

    private final CultureService cultureService;

    public CultureController(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    @GetMapping
    public List<Culture> getAllCultures() {
        return cultureService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Culture> getCultureById(@PathVariable Long id) {
        return cultureService.findById(id)
                .map(culture -> ResponseEntity.ok().body(culture))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Culture createCulture(@RequestBody Culture culture) {
        return cultureService.save(culture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Culture> updateCulture(@PathVariable Long id, @RequestBody Culture culture) {
        return cultureService.update(id, culture)
                .map(updatedCulture -> ResponseEntity.ok().body(updatedCulture))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCulture(@PathVariable Long id) {
        if (cultureService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
