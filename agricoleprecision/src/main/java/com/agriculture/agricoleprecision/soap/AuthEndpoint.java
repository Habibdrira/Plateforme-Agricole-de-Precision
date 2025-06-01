package com.agriculture.agricoleprecision.soap;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jakarta.xml.bind.annotation.*;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "http://agriculture.com/auth";
    private static final Logger logger = LoggerFactory.getLogger(AuthEndpoint.class);
    private static volatile String lastConnectedUser = null;
    private static volatile String lastConnectedRole = null;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {
        AuthResponse response = new AuthResponse();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(request.getUsername()).orElse(null);

        if (utilisateur != null && utilisateur.getPassword().equals(request.getPassword())) {
            response.setSuccess(true);
            response.setUsername(utilisateur.getUsername());
            response.setRole(utilisateur.getRole().name());
            response.setMessage("Authentification réussie");
            lastConnectedUser = request.getUsername();
            lastConnectedRole = utilisateur.getRole().name();
            logger.info("SOAP Login Successful: Username={}, Role={}", request.getUsername(), utilisateur.getRole());
        } else {
            response.setSuccess(false);
            response.setMessage("Nom d'utilisateur ou mot de passe incorrect");
            logger.warn("SOAP Login Failed: Username={}", request.getUsername());
        }
        return response;
    }

    public static String getLastConnectedUser() {
        return lastConnectedUser;
    }

    public static String getLastConnectedRole() {
        return lastConnectedRole;
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
    private String username;
    private String role;
    private String message;

    @XmlElement
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    @XmlElement
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @XmlElement
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @XmlElement
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
