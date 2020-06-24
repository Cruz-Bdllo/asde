package com.asde.app.repository;

import com.asde.app.domain.Representant;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>representantes</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link CrudRepository}.
 */
public interface RepresentantRepository extends CrudRepository<Representant, Integer> {
}
