package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.CultureService;
import com.agriculture.agricoleprecision.service.ParcelleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping({"/admin/cultures", "/agriculteur/cultures"})
public class CultureController {

    @Autowired
    private CultureService cultureService;

    @Autowired
    private ParcelleService parcelleService;

    @GetMapping
    public String listCultures(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("cultures", cultureService.findAll());
            model.addAttribute("isAdmin", true);
            return "culture_list";
        } else if (user.getRole() == Role.AGRICULTEUR) {
            List<Parcelle> userParcelles = parcelleService.findByUtilisateurId(user.getId());
            List<Culture> userCultures = userParcelles.stream()
                    .flatMap(p -> cultureService.findByParcelleId(p.getId()).stream())
                    .toList();
            model.addAttribute("cultures", userCultures);
            model.addAttribute("isAdmin", false);
            return "culture_list";
        }
        return "redirect:/login";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("culture", new Culture());
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("parcelles", parcelleService.findAll());
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("parcelles", parcelleService.findByUtilisateurId(user.getId()));
            model.addAttribute("isAdmin", false);
        }
        return "culture_form";
    }

    @PostMapping("/create")
    public String createCulture(@RequestParam String typeCulture, @RequestParam String datePlantation,
                                @RequestParam Long parcelleId, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Parcelle parcelle = parcelleService.findById(parcelleId).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !parcelle.getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/cultures";
        }
        cultureService.createCulture(typeCulture, LocalDate.parse(datePlantation), parcelleId);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/cultures" : "redirect:/agriculteur/cultures";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Culture culture = cultureService.findById(id).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/cultures";
        }
        model.addAttribute("culture", culture);
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("parcelles", parcelleService.findAll());
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("parcelles", parcelleService.findByUtilisateurId(user.getId()));
            model.addAttribute("isAdmin", false);
        }
        return "culture_form";
    }

    @PostMapping("/edit/{id}")
    public String updateCulture(@PathVariable Long id, @RequestParam String typeCulture,
                                @RequestParam String datePlantation, @RequestParam Long parcelleId,
                                HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Culture culture = cultureService.findById(id).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/cultures";
        }
        cultureService.updateCulture(id, typeCulture, LocalDate.parse(datePlantation), parcelleId);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/cultures" : "redirect:/agriculteur/cultures";
    }

    @GetMapping("/delete/{id}")
    public String deleteCulture(@PathVariable Long id, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Culture culture = cultureService.findById(id).orElseThrow();
        if (user.getRole() == Role.AGRICULTEUR && !culture.getParcelle().getUtilisateur().getId().equals(user.getId())) {
            return "redirect:/agriculteur/cultures";
        }
        cultureService.deleteById(id);
        return user.getRole() == Role.ADMIN ? "redirect:/admin/cultures" : "redirect:/agriculteur/cultures";
    }
}
