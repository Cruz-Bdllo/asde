package com.asde.app.repository;

import com.asde.app.domain.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

    Optional<Procedure> findProcedureByName(String name);

} // end repository
