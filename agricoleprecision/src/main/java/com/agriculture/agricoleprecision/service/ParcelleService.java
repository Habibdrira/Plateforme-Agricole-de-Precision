package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.repository.ParcelleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelleService {

    private final ParcelleRepository parcelleRepository;

    public ParcelleService(ParcelleRepository parcelleRepository) {
        this.parcelleRepository = parcelleRepository;
    }

    public List<Parcelle> findAll() {
        return parcelleRepository.findAll();
    }

    public Optional<Parcelle> findById(Long id) {
        return parcelleRepository.findById(id);
    }

    public List<Parcelle> findByUtilisateurId(Long utilisateurId) {
        return parcelleRepository.findByUtilisateurId(utilisateurId);
    }

    public Parcelle save(Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    public Optional<Parcelle> update(Long id, Parcelle parcelleDetails) {
        return parcelleRepository.findById(id).map(parcelle -> {
            parcelle.setLocalisation(parcelleDetails.getLocalisation());
            parcelle.setSurface(parcelleDetails.getSurface());
            parcelle.setTypeSol(parcelleDetails.getTypeSol());
            parcelle.setUtilisateur(parcelleDetails.getUtilisateur());
            return parcelleRepository.save(parcelle);
        });
    }

    public boolean delete(Long id) {
        return parcelleRepository.findById(id).map(parcelle -> {
            parcelleRepository.delete(parcelle);
            return true;
        }).orElse(false);
    }
}
