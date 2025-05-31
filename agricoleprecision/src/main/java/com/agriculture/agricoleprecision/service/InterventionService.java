package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.Intervention;
import com.agriculture.agricoleprecision.repository.InterventionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterventionService {

    private final InterventionRepository repository;

    public InterventionService(InterventionRepository repository) {
        this.repository = repository;
    }

    public List<Intervention> findAll() {
        return repository.findAll();
    }

    public Optional<Intervention> findById(Long id) {
        return repository.findById(id);
    }

    public Intervention save(Intervention intervention) {
        return repository.save(intervention);
    }

    public Optional<Intervention> update(Long id, Intervention newData) {
        return repository.findById(id).map(existing -> {
            existing.setDateIntervention(newData.getDateIntervention());
            existing.setTypeIntervention(newData.getTypeIntervention());
            existing.setDescription(newData.getDescription());
            existing.setParcelle(newData.getParcelle());
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(intervention -> {
            repository.delete(intervention);
            return true;
        }).orElse(false);
    }

    public List<Intervention> findByParcelleId(Long parcelleId) {
        return repository.findByParcelleId(parcelleId);
    }

    public List<Intervention> findByType(String type) {
        return repository.findByTypeIntervention(type);
    }
}
