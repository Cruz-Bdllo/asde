package com.asde.app.repository;

import com.asde.app.domain.Alcaldia;
import org.springframework.data.repository.CrudRepository;


/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>alcaldias</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link CrudRepository}.
 */
public interface AlcaldiaRepository extends CrudRepository<Alcaldia, Integer> {
} // end repository
