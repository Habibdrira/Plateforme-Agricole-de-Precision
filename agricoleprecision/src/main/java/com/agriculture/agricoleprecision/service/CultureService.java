package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.repository.CultureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CultureService {

    private final CultureRepository cultureRepository;

    public CultureService(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }

    public List<Culture> findAll() {
        return cultureRepository.findAll();
    }

    public Optional<Culture> findById(Long id) {
        return cultureRepository.findById(id);
    }

    public Culture save(Culture culture) {
        return cultureRepository.save(culture);
    }

    public Optional<Culture> update(Long id, Culture cultureDetails) {
        return cultureRepository.findById(id).map(culture -> {
            culture.setNom(cultureDetails.getNom());
            culture.setDateDebut(cultureDetails.getDateDebut());
            culture.setDateFin(cultureDetails.getDateFin());
            culture.setParcelle(cultureDetails.getParcelle());
            return cultureRepository.save(culture);
        });
    }

    public boolean delete(Long id) {
        return cultureRepository.findById(id).map(culture -> {
            cultureRepository.delete(culture);
            return true;
        }).orElse(false);
    }
}
