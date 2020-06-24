package com.asde.app.repository;

import com.asde.app.domain.Requirement;
import org.springframework.data.repository.CrudRepository;


/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>requisitos</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link CrudRepository}.
 */

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {
} // end repository
