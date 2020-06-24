package com.asde.app.service.alcaldia;

import com.asde.app.domain.Alcaldia;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>alcaldias</b>, definiendo nombres mas comodos a los que ofrecen las interfaces
 * CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */

public interface IAlcaldiaService {

    /**
     * Obtendrá todos los registros de la tabla <b>alcaldias</b> en la BD.
     * @return Lista de objetos de la entidad <b>Alcaldia</b>
     */
    List<Alcaldia> getAllAlcaldias();


    /**
     * Obtendrá un registro en especifico definido en su parámetro.
     * @param idAlcaldia Integer Identificador único a buscar.
     * @return Objeto de la entidad <b>Alcaldia</b>.
     */
    Alcaldia getAlcaldiaById(Integer idAlcaldia);


    /**
     * Guardará la entidad <b>Alcaldia</b> como registro en la tabla <b>alcaldias</b> de la BD.
     * @param alcaldia Objeto de la entidad <b>Alcaldia</b> a guardar.
     */
    void saveAlcaldia(Alcaldia alcaldia);


    /**
     * Eliminará un registro de la tabla <b>alcaldias</b> dado su identificador único en su parámetro.
     * @param idAlcaldia Identificador único a eliminar.
     */
    void deleteAlcaldia(Integer idAlcaldia);

} // end service interface
