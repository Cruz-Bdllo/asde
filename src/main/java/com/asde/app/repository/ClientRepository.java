package com.asde.app.repository;

import com.asde.app.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
} // end repository
