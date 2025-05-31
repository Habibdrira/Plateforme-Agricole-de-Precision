package com.agriculture.agricoleprecision.repository;

import com.agriculture.agricoleprecision.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByParcelleId(Long parcelleId);
    List<Intervention> findByTypeIntervention(String typeIntervention);
}
