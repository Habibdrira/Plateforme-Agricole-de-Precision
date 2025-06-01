package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import com.agriculture.agricoleprecision.enums.Role;
import com.agriculture.agricoleprecision.soap.AuthEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElse(null);
        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            session.setAttribute("user", utilisateur);
            if (utilisateur.getRole() == Role.ADMIN) {
                return "redirect:/admin";
            } else if (utilisateur.getRole() == Role.AGRICULTEUR) {
                return "redirect:/agriculteur";
            }
        }
        model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null && user.getRole() == Role.ADMIN) {
            String lastSoapUser = AuthEndpoint.getLastConnectedUser();
            String lastSoapRole = AuthEndpoint.getLastConnectedRole();
            model.addAttribute("lastSoapUser", lastSoapUser != null ? lastSoapUser : "None");
            model.addAttribute("lastSoapRole", lastSoapRole != null ? lastSoapRole : "None");
            return "admin";
        }
        return "redirect:/login";
    }

    @GetMapping("/agriculteur")
    public String agriculteurPage(HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user != null && user.getRole() == Role.AGRICULTEUR) {
            return "agriculteur";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}