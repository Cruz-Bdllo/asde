package com.asde.app.repository;

import com.asde.app.domain.Signature;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>firmas</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link CrudRepository}.
 */

public interface SignatureRepository extends CrudRepository<Signature, Integer> {
} // end of repository
