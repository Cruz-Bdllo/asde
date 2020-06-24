package com.asde.app.service.requirement;

import com.asde.app.domain.Representant;
import com.asde.app.domain.Requirement;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>requisitos</b>, definiendo nombres mas comodos a los que ofrece la
 * interfaz CRUD {@link org.springframework.data.repository.CrudRepository}.
 */
public interface IRequirementService {

    /**
     * Obtendrá todos los registros de la tabla <b>requisitos</b> en la BD.
     * @return Lista de objetos de la entidad {@link Requirement}.
     */
    List<Requirement> getAllRequirements();


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el id auto generado por la BD.
     * @param idRequirement Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Requirement}.
     */
    Requirement findRequirementById(Integer idRequirement);


    /**
     * Guardará la entidad {@link Requirement} como registro en la tabla <b>requisitos</b> de la BD.
     * @param requirement Objeto de la entidad {@link Requirement} a guardar.
     */
    void saveRequirement(Requirement requirement);


    /**
     * Eliminará un registro de la tabla <b>requisitos</b> dado su identificador único en su parámetro.
     * @param idRequirement Identificador único a eliminar.
     */
    void deleteRequirementById(Integer idRequirement);

}
