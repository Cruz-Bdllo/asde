package com.asde.app.repository;


import com.asde.app.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
    Optional<Income> getIncomeByCodIncome(String codIncome);
}
