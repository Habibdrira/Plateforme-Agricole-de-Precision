package com.agriculture.agricoleprecision.soap;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jakarta.xml.bind.annotation.*;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "http://agriculture.com/auth";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {
        System.out.println("Requête reçue - Username: " + request.getUsername() + ", Password: " + request.getPassword());
        AuthResponse response = new AuthResponse();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(request.getUsername()).orElse(null);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé pour username: " + request.getUsername());
            response.setSuccess(false);
        } else {
            System.out.println("Utilisateur trouvé - Username: " + utilisateur.getUsername() + ", Password: " + utilisateur.getPassword());
            boolean passwordMatch = utilisateur.getPassword().equals(request.getPassword());
            System.out.println("Comparaison des mots de passe - Résultat: " + passwordMatch);
            response.setSuccess(passwordMatch);
        }
        return response;
    }
}

@XmlRootElement(namespace = "http://agriculture.com/auth", name = "AuthRequest")
class AuthRequest {
    private String username;
    private String password;

    @XmlElement
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @XmlElement
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

@XmlRootElement(namespace = "http://agriculture.com/auth", name = "AuthResponse")
class AuthResponse {
    private boolean success;

    @XmlElement
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}