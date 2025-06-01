package com.agriculture.agricoleprecision.repository;

import com.agriculture.agricoleprecision.model.Parcelle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParcelleRepository extends JpaRepository<Parcelle, Long> {
    List<Parcelle> findByUtilisateurId(Long utilisateurId);
}
