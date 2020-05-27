package com.asde.app.repository;

import com.asde.app.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
} // end repository
