package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.ConseilAgricole;
import com.agriculture.agricoleprecision.service.ConseilAgricoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conseils")
public class ConseilAgricoleController {

    private final ConseilAgricoleService service;

    public ConseilAgricoleController(ConseilAgricoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConseilAgricole> getAllConseils() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConseilAgricole> getConseilById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConseilAgricole createConseil(@RequestBody ConseilAgricole conseil) {
        return service.save(conseil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConseilAgricole> updateConseil(@PathVariable Long id, @RequestBody ConseilAgricole conseil) {
        return service.update(id, conseil)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConseil(@PathVariable Long id) {
        return service.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
