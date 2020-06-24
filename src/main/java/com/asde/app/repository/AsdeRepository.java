package com.asde.app.repository;


import com.asde.app.domain.Asde;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>asde</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link CrudRepository}.
 */

public interface AsdeRepository extends CrudRepository<Asde, Integer> {

} // end interface
