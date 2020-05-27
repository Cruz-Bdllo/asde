package com.asde.app.repository;

import com.asde.app.domain.Signature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signature, Integer> {
} // end of repository
