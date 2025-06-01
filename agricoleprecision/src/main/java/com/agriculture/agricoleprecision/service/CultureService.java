package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Culture;
import com.agriculture.agricoleprecision.model.Parcelle;
import com.agriculture.agricoleprecision.repository.CultureRepository;
import com.agriculture.agricoleprecision.repository.ParcelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CultureService {

    @Autowired
    private CultureRepository cultureRepository;

    @Autowired
    private ParcelleRepository parcelleRepository;

    public List<Culture> findAll() {
        return cultureRepository.findAll();
    }

    public Optional<Culture> findById(Long id) {
        return cultureRepository.findById(id);
    }

    public List<Culture> findByParcelleId(Long parcelleId) {
        return cultureRepository.findByParcelleId(parcelleId);
    }

    public Culture createCulture(String typeCulture, LocalDate datePlantation, Long parcelleId) {
        Parcelle parcelle = parcelleRepository.findById(parcelleId).orElseThrow();
        Culture culture = new Culture(typeCulture, datePlantation, parcelle);
        return cultureRepository.save(culture);
    }

    public Culture updateCulture(Long id, String typeCulture, LocalDate datePlantation, Long parcelleId) {
        Optional<Culture> existing = cultureRepository.findById(id);
        if (existing.isPresent()) {
            Culture culture = existing.get();
            culture.setTypeCulture(typeCulture);
            culture.setDatePlantation(datePlantation);
            Parcelle parcelle = parcelleRepository.findById(parcelleId).orElseThrow();
            culture.setParcelle(parcelle);
            return cultureRepository.save(culture);
        }
        return null;
    }

    public void deleteById(Long id) {
        cultureRepository.deleteById(id);
    }
}
