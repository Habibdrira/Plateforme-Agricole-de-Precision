package com.agriculture.agricoleprecision.service;

import com.agriculture.agricoleprecision.model.ConseilAgricole;
import com.agriculture.agricoleprecision.repository.ConseilAgricoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConseilAgricoleService {

    private final ConseilAgricoleRepository repository;

    public ConseilAgricoleService(ConseilAgricoleRepository repository) {
        this.repository = repository;
    }

    public List<ConseilAgricole> findAll() {
        return repository.findAll();
    }

    public Optional<ConseilAgricole> findById(Long id) {
        return repository.findById(id);
    }

    public ConseilAgricole save(ConseilAgricole conseil) {
        return repository.save(conseil);
    }

    public Optional<ConseilAgricole> update(Long id, ConseilAgricole details) {
        return repository.findById(id).map(existing -> {
            existing.setSaison(details.getSaison());
            existing.setCultureAssociee(details.getCultureAssociee());
            existing.setDescription(details.getDescription());
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(conseil -> {
            repository.delete(conseil);
            return true;
        }).orElse(false);
    }
}
