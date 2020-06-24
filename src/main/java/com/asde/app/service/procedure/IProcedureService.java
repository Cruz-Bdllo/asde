package com.asde.app.service.procedure;

import com.asde.app.domain.Income;
import com.asde.app.domain.Procedure;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>tramites</b>, definiendo nombres mas comodos a los que ofrecen las
 * interfaces CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface IProcedureService {

    /**
     * Obtendrá todos los registros de la tabla <b>tramites</b> en la BD.
     * @return Lista de objetos de la entidad {@link Procedure}.
     */
    List<Procedure> getAllProcedures();


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el id auto generado por la BD.
     * @param idProcedure Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Procedure}.
     */
    Procedure findProcedureById(Integer idProcedure);


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el nombre único del trámite.
     * @param name Integer Identificador único a buscar (nombre).
     * @return Objeto de la entidad {@link Procedure}.
     */
    Procedure findProcedureByName(String name);


    /**
     * Guardará la entidad {@link Procedure} como registro en la tabla <b>tramites</b> de la BD.
     * @param procedure Objeto de la entidad {@link Procedure} a guardar.
     */
    void saveProcedure(Procedure procedure);


    /**
     * Eliminará un registro de la tabla <b>tramites</b> dado su identificador único en su parámetro.
     * @param idProcedure Identificador único a eliminar.
     */
    void deleteProcedureById(Integer idProcedure);

} // end service interface
