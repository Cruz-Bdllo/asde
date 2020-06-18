package com.asde.app.repository;

import com.asde.app.domain.Representant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepresentantRepository extends JpaRepository<Representant, Integer> {
}
