package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String listUsers(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("users", utilisateurService.findAll());
        return "user_list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("roles", Role.values());
        return "user_form";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password,
                             @RequestParam String role, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        utilisateurService.createUtilisateur(username, password, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        Utilisateur utilisateur = utilisateurService.findById(id).orElseThrow();
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("roles", Role.values());
        return "user_form";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String username,
                             @RequestParam String password, @RequestParam String role,
                             HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        utilisateurService.updateUtilisateur(id, username, password, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        utilisateurService.deleteById(id);
        return "redirect:/admin/users";
    }

    private boolean isAdmin(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        return user != null && user.getRole() == Role.ADMIN;
    }
}