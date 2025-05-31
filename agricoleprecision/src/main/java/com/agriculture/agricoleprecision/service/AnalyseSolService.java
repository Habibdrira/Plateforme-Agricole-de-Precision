package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.AnalyseSol;
import com.agriculture.agricoleprecision.repository.AnalyseSolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseSolService {

    private final AnalyseSolRepository analyseSolRepository;

    public AnalyseSolService(AnalyseSolRepository analyseSolRepository) {
        this.analyseSolRepository = analyseSolRepository;
    }

    public List<AnalyseSol> findAll() {
        return analyseSolRepository.findAll();
    }

    public Optional<AnalyseSol> findById(Long id) {
        return analyseSolRepository.findById(id);
    }

    public AnalyseSol save(AnalyseSol analyseSol) {
        return analyseSolRepository.save(analyseSol);
    }

    public Optional<AnalyseSol> update(Long id, AnalyseSol analyseSolDetails) {
        return analyseSolRepository.findById(id).map(analyseSol -> {
            analyseSol.setDateAnalyse(analyseSolDetails.getDateAnalyse());
            analyseSol.setPh(analyseSolDetails.getPh());
            analyseSol.setHumidite(analyseSolDetails.getHumidite());
            analyseSol.setTemperature(analyseSolDetails.getTemperature());
            analyseSol.setParcelle(analyseSolDetails.getParcelle());
            return analyseSolRepository.save(analyseSol);
        });
    }

    public boolean delete(Long id) {
        return analyseSolRepository.findById(id).map(analyseSol -> {
            analyseSolRepository.delete(analyseSol);
            return true;
        }).orElse(false);
    }
}
