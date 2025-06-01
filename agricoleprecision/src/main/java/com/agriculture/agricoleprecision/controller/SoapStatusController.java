package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.soap.AuthEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws")
public class SoapStatusController {

    @GetMapping("/last-connected")
    public String getLastConnectedUser() {
        String username = AuthEndpoint.getLastConnectedUser();
        String role = AuthEndpoint.getLastConnectedRole();
        if (username != null && role != null) {
            return "Last connected SOAP user: " + username + " (Role: " + role + ")";
        } else {
            return "No user has logged in via SOAP yet.";
        }
    }
}