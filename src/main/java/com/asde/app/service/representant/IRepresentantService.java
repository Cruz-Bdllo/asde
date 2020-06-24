package com.asde.app.service.representant;


import com.asde.app.domain.Procedure;
import com.asde.app.domain.Representant;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>representantes</b>, definiendo nombres mas comodos a la que ofrece la
 * interfaz CRUD {@link org.springframework.data.repository.CrudRepository}.
 */
public interface IRepresentantService {

    /**
     * Obtendrá todos los registros de la tabla <b>representantes</b> en la BD.
     * @return Lista de objetos de la entidad {@link Representant}.
     */
    List<Representant> getAllRepresentants();


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el id auto generado por la BD.
     * @param idRepresentant Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Representant}.
     */
    Representant getRepresentantById(Integer idRepresentant);


    /**
     * Guardará la entidad {@link Representant} como registro en la tabla <b>representantes</b> de la BD.
     * @param representant Objeto de la entidad {@link Representant} a guardar.
     */
    void saveRepresentant(Representant representant);


    /**
     * Eliminará un registro de la tabla <b>representantes</b> dado su identificador único en su parámetro.
     * @param IdRepresentant Identificador único a eliminar.
     */
    void deleteRepresentantById(Integer IdRepresentant);

}
