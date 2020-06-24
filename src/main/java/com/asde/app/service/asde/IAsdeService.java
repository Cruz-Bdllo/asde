package com.asde.app.service.asde;

import com.asde.app.domain.Asde;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>asde</b>, definiendo nombres mas comodos a los que ofrecen las interfaces
 * CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */

public interface IAsdeService {

    /**
     * Obtendrá el primer registro almacenado en la tabla <b>asde</b>.
     * @return Objeto de la entidad <b>Asde</b>.
     */
    Asde getAsde();


    /**
     * Guardará la entidad <b>Asde</b> como registro en la tabla <b>asde</b> de la BD.
     * @param asde Objeto de la entidad <b>Asde</b> a guardar.
     */
    void saveAsde(Asde asde);

}
