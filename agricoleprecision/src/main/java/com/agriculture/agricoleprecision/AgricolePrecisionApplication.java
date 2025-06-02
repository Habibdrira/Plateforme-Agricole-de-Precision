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
		System.out.println("Liens pour tester (02/06/2025, 10:40 AM CET):");
		System.out.println("1. SOAP Authentication (WSDL): http://localhost:8080/ws/auth.wsdl");
		System.out.println("2. REST - Lister parcelles (GET): http://localhost:8080/api/parcelles");
		System.out.println("3. REST - Créer parcelle (POST): http://localhost:8080/api/parcelles");
		System.out.println("4. GraphQL (POST): http://localhost:8080/graphql");
		System.out.println("   Requête: { \"query\": \"query { parcellesByUtilisateur(utilisateurId: 1) { id nom } }\" }");
	}
}
