package com.asde.app.service.dependence;

import com.asde.app.domain.Client;
import com.asde.app.domain.Dependence;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>dependencias</b>, definiendo nombres mas comodos a los que ofrecen las
 * interfaces CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */

public interface IDependenceService {

    /**
     * Obtendrá todos los registros de la tabla <b>dependencias</b> en la BD.
     * @return Lista de objetos de la entidad {@link Dependence}.
     */
    List<Dependence> getAllDependecies();


    /**
     * Obtendrá un registro en especifico definido en su parámetro.
     * @param idDependence Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Dependence}.
     */
    Dependence getDependencyById(Integer idDependence);


    /**
     * Guardará la entidad {@link Dependence} como registro en la tabla <b>empresa</b> de la BD.
     * @param dependence Objeto de la entidad {@link Dependence} a guardar.
     */
    void saveDependence(Dependence dependence);


    /**
     * Eliminará un registro de la tabla <b>dependencias</b> dado su identificador único en su parámetro.
     * @param idDependence Identificador único a eliminar.
     */
    void deleteDependenceById(Integer idDependence);

}
