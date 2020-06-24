package com.asde.app.service.client;

import com.asde.app.domain.Client;
import com.asde.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IClientService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones basicas CRUD a la tabla <b>empresa</b>.
 */

@Service
public class ClientService implements IClientService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private ClientRepository clientRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public ClientService(ClientRepository clientRepo){ this.clientRepo = clientRepo; }


    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>empresa</b>.
     * @return Lista de objetos Client.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única de la
     * tabla <b>empresa</b>.
     * @param idClient Clave única de identificación.
     * @return Objeto <b>Client</b> si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Client getClientById(Integer idClient) {
        return clientRepo.findById(idClient).orElse(null);
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su Registro Federal de Contribuyente (RFC)
     * de la tabla <b>empresa</b>.
     * @param rfc Clave única de identificación.
     * @return Objeto <b>Client</b> si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Client getClientByRfc(String rfc) {
        return clientRepo.findClientByRfc(rfc).orElse(null);
    }


    /**
     * Método que guarda la entidad <b>Client</b> en la BD en la tabla <b>empresa</b>.
     * @param client Objeto <b>Client</b> a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveClient(Client client) {
        clientRepo.save(client);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>empresa</b> dada una clave única
     * de identificación.
     * @param idClient Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteClientById(Integer idClient) {
        clientRepo.deleteById(idClient);
    }
} // end of service to client
