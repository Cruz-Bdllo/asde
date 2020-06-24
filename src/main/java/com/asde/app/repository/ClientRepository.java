package com.asde.app.repository;

import com.asde.app.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>asde</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link JpaRepository}, támbien hace uso de la interfaz {@link PagingAndSortingRepository} para poder
 * paginar los registros en caso de que sean demasiados teniendo un mejor control en el despliegue de los mismos.
 */

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientByRfc(String rfc);
} // end repository
