package com.asde.app.repository;

import com.asde.app.domain.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Interfaz que permite usar las operaciones básicas para manipular la tabla <b>tramites</b> de la BD, asignando
 * como clave primaria un objeto de tipo <b>Integer</b>, esto lo realiza gracias a la herencia de la interfaz
 * {@link JpaRepository}, támbien hace uso de la interfaz {@link PagingAndSortingRepository} para poder
 * paginar los registros en caso de que sean demasiados teniendo un mejor control en el despliegue de los mismos.
 */
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

    /**
     * Método que personaliza una query, obtiendo un objeto <b>Procedure</b> de la BD de acuerdo al identificador
     * único que se le proporcione como parámetro.
     * @param name String nombre del trámite a buscar, este es un campo único.
     * @return El objeto <b>Procedure</b> de la BD o <b>null</b> en caso contrario.
     */
    Optional<Procedure> findProcedureByName(String name);

} // end repository
