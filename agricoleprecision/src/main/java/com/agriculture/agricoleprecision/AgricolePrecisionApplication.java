package com.agriculture.agricoleprecision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AgricolePrecisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgricolePrecisionApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void displayApplicationUrls() {
		String baseUrl = "http://localhost:8080";
		System.out.println("=== Plateforme Agricole de Précision - URLs pour tests ===");
		System.out.println("\n1. Authentification Web (Thymeleaf):");
		System.out.println("   - Page de connexion: " + baseUrl + "/login");
		System.out.println("   - Tableau de bord Admin: " + baseUrl + "/admin");
		System.out.println("   - Tableau de bord Agriculteur: " + baseUrl + "/agriculteur");
		System.out.println("   - Déconnexion: " + baseUrl + "/logout");

		System.out.println("\n2. Gestion Utilisateurs (Admin seulement):");
		System.out.println("   - Liste: " + baseUrl + "/admin/users");
		System.out.println("   - Créer: " + baseUrl + "/admin/users/create");
		System.out.println("   - Modifier (ex. ID=1): " + baseUrl + "/admin/users/edit/1");
		System.out.println("   - Supprimer (ex. ID=1): " + baseUrl + "/admin/users/delete/1");

		System.out.println("\n3. Gestion Parcelles (Admin/agruculteur):");
		System.out.println("   - Liste (Admin): " + baseUrl + "/admin/parcelles");
		System.out.println("   - Liste (Agriculteur): " + baseUrl + "/agriculteur/parcelles");
		System.out.println("   - Créer (Admin): " + baseUrl + "/admin/parcelles/create");
		System.out.println("   - Créer (Agriculteur): " + baseUrl + "/agriculteur/parcelles/create");
		System.out.println("   - Modifier (ex. ID=1, Admin): " + baseUrl + "/admin/parcelles/edit/1");
		System.out.println("   - Supprimer (ex. ID=1, Agriculteur): " + baseUrl + "/agriculteur/parcelles/delete/1");

		System.out.println("\n4. Gestion Cultures (Admin/Agriculteur):");
		System.out.println("   - Liste (Admin): " + baseUrl + "/admin/cultures");
		System.out.println("   - Liste (Agriculteur): " + baseUrl + "/agriculteur/cultures");
		System.out.println("   - Créer (Admin): " + baseUrl + "/admin/cultures/create");
		System.out.println("   - Créer (Agriculteur): " + baseUrl + "/agriculteur/cultures/create");
		System.out.println("   - Modifier (ex. ID=1, Admin): " + baseUrl + "/admin/cultures/edit/1");
		System.out.println("   - Supprimer (ex. ID=1, Agriculteur): " + baseUrl + "/agriculteur/cultures/delete/1");

		System.out.println("\n5. REST API Endpoints:");
		System.out.println("   - Utilisateurs: ");
		System.out.println("     - GET " + baseUrl + "/api/users (Liste)");
		System.out.println("     - POST " + baseUrl + "/api/users (Créer)");
		System.out.println("     - PUT " + baseUrl + "/api/users/1 (Modifier)");
		System.out.println("     - DELETE " + baseUrl + "/api/users/1 (Supprimer)");
		System.out.println("   - Parcelles: ");
		System.out.println("     - GET " + baseUrl + "/api/parcelles (Liste)");
		System.out.println("     - POST " + baseUrl + "/api/parcelles (Créer)");
		System.out.println("     - PUT " + baseUrl + "/api/parcelles/1 (Modifier)");
		System.out.println("     - DELETE " + baseUrl + "/api/parcelles/1 (Supprimer)");
		System.out.println("   - Cultures: ");
		System.out.println("     - GET " + baseUrl + "/api/cultures (Liste)");
		System.out.println("     - POST " + baseUrl + "/api/cultures (Créer)");
		System.out.println("     - PUT " + baseUrl + "/api/cultures/1 (Modifier)");
		System.out.println("     - DELETE " + baseUrl + "/api/cultures/1 (Supprimer)");

		System.out.println("\n6. SOAP Authentification:");
		System.out.println("   - WSDL: " + baseUrl + "/ws/auth.wsdl");
		System.out.println("   - Endpoint: " + baseUrl + "/ws");

		System.out.println("\n7. GraphQL:");
		System.out.println("   - GraphiQL Interface: " + baseUrl + "/graphiql");
		System.out.println("   - Endpoint: " + baseUrl + "/graphql");
		System.out.println("=================================================");
	}
}
