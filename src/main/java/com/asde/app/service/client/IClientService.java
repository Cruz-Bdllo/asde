package com.asde.app.service.client;

import com.asde.app.domain.Client;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>empresa</b>, definiendo nombres mas comodos a los que ofrecen las interfaces
 * CRUD {@link org.springframework.data.repository.CrudRepository} o
 * {@link org.springframework.data.jpa.repository.JpaRepository}
 */

public interface IClientService {

    /**
     * Obtendrá todos los registros de la tabla <b>empresa</b> en la BD.
     * @return Lista de objetos de la entidad {@link Client}.
     */
    List<Client> getAllClients();


    /**
     * Obtendrá un registro en especifico definido en su parámetro.
     * @param idClient Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Client}.
     */
    Client getClientById(Integer idClient);


    /**
     * Obtendrá un registro en especifico por medio de su Registro Federal de Contribuyente (RFC) definido en su
     * parámetro.
     * @param rfc Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Client}.
     */
    Client getClientByRfc(String rfc);


    /**
     * Guardará la entidad {@link Client} como registro en la tabla <b>empresa</b> de la BD.
     * @param client Objeto de la entidad {@link Client} a guardar.
     */
    void saveClient(Client client);


    /**
     * Eliminará un registro de la tabla <b>empresa</b> dado su identificador único en su parámetro.
     * @param idClient Identificador único a eliminar.
     */
    void deleteClientById(Integer idClient);

}
