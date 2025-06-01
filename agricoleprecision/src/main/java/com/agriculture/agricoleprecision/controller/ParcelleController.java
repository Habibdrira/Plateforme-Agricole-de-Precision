package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.ParcelleService;
import com.agriculture.agricoleprecision.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping({"/admin/parcelles", "/agriculteur/parcelles"})
public class ParcelleController {

    @Autowired
    private ParcelleService parcelleService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String listParcelles(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("parcelles", parcelleService.findAll());
            model.addAttribute("isAdmin", true);
            return "parcelle_list";
        } else if (user.getRole() == Role.AGRICULTEUR) {
            model.addAttribute("parcelles", parcelleService.findByUtilisateurId(user.getId()));
            model.addAttribute("isAdmin", false);
            return "parcelle_list";
        }
        return "redirect:/login";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("parcelle", new Parcelle());
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("users", utilisateurService.findAll());
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("users", List.of(user));
            model.addAttribute("isAdmin", false);
        }
        return "parcelle_form";
    }

    @PostMapping("/create")
    public String createParcelle(@RequestParam String nom, @RequestParam String localisation,
                                 @RequestParam double surface, @RequestParam String typeSol,
                                 @RequestParam Long utilisateurId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (user.getRole() == Role.AGRICULTEUR && !user.getId().equals(utilisateurId)) {
            return "redirect:/agriculteur/parcelles";
        }
        parcelleService.createParcelle(nom, localisation, surface, typeSol, utilisateurId);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/parcelles" : "redirect:/agriculteur/parcelles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Parcelle parcelle = parcelleService.findById(id).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/parcelles";
        }
        model.addAttribute("parcelle", parcelle);
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("users", utilisateurService.findAll());
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("users", List.of(user));
            model.addAttribute("isAdmin", false);
        }
        return "parcelle_form";
    }

    @PostMapping("/edit/{id}")
    public String updateParcelle(@PathVariable Long id, @RequestParam String nom,
                                 @RequestParam String localisation, @RequestParam double surface,
                                 @RequestParam String typeSol, @RequestParam Long utilisateurId,
                                 HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (user.getRole() == Role.AGRICULTEUR && !user.getId().equals(utilisateurId)) {
            return "redirect:/agriculteur/parcelles";
        }
        parcelleService.updateParcelle(id, nom, localisation, surface, typeSol, utilisateurId);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/parcelles" : "redirect:/agriculteur/parcelles";
    }

    @GetMapping("/delete/{id}")
    public String deleteParcelle(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Parcelle parcelle = parcelleService.findById(id).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/parcelles";
        }
        parcelleService.deleteById(id);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/parcelles" : "redirect:/agriculteur/parcelles";
    }
}
