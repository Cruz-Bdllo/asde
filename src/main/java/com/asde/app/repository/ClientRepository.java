package com.asde.app.repository;

import com.asde.app.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByRfc(String rfc);
} // end repository
