package com.agriculture.agricoleprecision.graphql;

import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.repository.UtilisateurRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class GraphQLConfig {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() throws IOException {
        System.out.println("Chargement du schéma GraphQL...");
        String sdl = new String(new ClassPathResource("schema.graphqls").getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("Schéma chargé avec succès : " + sdl);
        return builder -> builder
                .type("Query", wiring -> wiring
                        .dataFetcher("utilisateurs", utilisateursDataFetcher()));
    }

    private DataFetcher<List<Utilisateur>> utilisateursDataFetcher() {
        return environment -> {
            System.out.println("Exécution de la requête 'utilisateurs'...");
            List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
            System.out.println("Nombre d'utilisateurs trouvés : " + utilisateurs.size());
            return utilisateurs;
        };
    }
}