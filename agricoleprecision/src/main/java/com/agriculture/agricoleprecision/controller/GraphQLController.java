package com.agriculture.agricoleprecision.controller;

import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.model.Utilisateur;
import com.agriculture.agricoleprecision.service.CultureService;
import com.agriculture.agricoleprecision.service.ParcelleService;
import com.agriculture.agricoleprecision.service.UtilisateurService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ParcelleService parcelleService;

    @Autowired
    private CultureService cultureService;

    @QueryMapping
    public List<Utilisateur> users() {
        return utilisateurService.findAll();
    }

    @QueryMapping
    public Utilisateur userById(@Argument Long id) {
        return utilisateurService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Parcelle> parcelles() {
        return parcelleService.findAll();
    }

    @QueryMapping
    public Parcelle parcelleById(@Argument Long id) {
        return parcelleService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Culture> cultures() {
        return cultureService.findAll();
    }

    @QueryMapping
    public Culture cultureById(@Argument Long id) {
        return cultureService.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Parcelle> parcellesByUtilisateurId(@Argument Long utilisateurId) {
        return parcelleService.findByUtilisateurId(utilisateurId);
    }

    @QueryMapping
    public List<Culture> culturesByParcelleId(@Argument Long parcelleId) {
        return cultureService.findByParcelleId(parcelleId);
    }
}
