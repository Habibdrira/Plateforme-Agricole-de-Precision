package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.CultureService;
import com.agriculture.agricoleprecision.service.ParcelleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cultures")
public class CultureRestController {

    @Autowired
    private CultureService cultureService;

    @Autowired
    private ParcelleService parcelleService;

    @GetMapping
    public ResponseEntity<List<Culture>> getAllCultures(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (user.getRole() == Role.ADMIN) {
            return ResponseEntity.ok(cultureService.findAll());
        } else if (user.getRole() == Role.AGRICULTEUR) {
            List<Parcelle> userParcelles = parcelleService.findByUtilisateurId(user.getId());
            List<Culture> userCultures = userParcelles.stream()
                    .flatMap(p -> cultureService.findByParcelleId(p.getId()).stream())
                    .toList();
            return ResponseEntity.ok(userCultures);
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Culture> getCultureById(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Culture culture = cultureService.findById(id).orElse(null);
        if (culture == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.ADMIN || culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.ok(culture);
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping
    public ResponseEntity<Culture> createCulture(@RequestParam String typeCulture, @RequestParam String datePlantation,
                                                 @RequestParam Long parcelleId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Parcelle parcelle = parcelleService.findById(parcelleId).orElse(null);
        if (parcelle == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        Culture culture = cultureService.createCulture(typeCulture, LocalDate.parse(datePlantation), parcelleId);
        return ResponseEntity.ok(culture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Culture> updateCulture(@PathVariable Long id, @RequestParam String typeCulture,
                                                 @RequestParam String datePlantation, @RequestParam Long parcelleId,
                                                 HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Culture culture = cultureService.findById(id).orElse(null);
        if (culture == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        Culture updated = cultureService.updateCulture(id, typeCulture, LocalDate.parse(datePlantation), parcelleId);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCulture(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Culture culture = cultureService.findById(id).orElse(null);
        if (culture == null) {
            return ResponseEntity.notFound().build();
        }
        if (user.getRole() == Role.AGRICULTEUR && !culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        cultureService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
