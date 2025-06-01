package com.agriculture.agricoleprecision.repository;

import com.agriculture.agricoleprecision.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CultureRepository extends JpaRepository<Culture, Long> {
    List<Culture> findByParcelleId(Long parcelleId);
}