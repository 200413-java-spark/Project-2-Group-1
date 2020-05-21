package com.github.sparkWeb.repositories;

import com.github.sparkWeb.models.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
