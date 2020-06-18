package com.asde.app.service;

import com.asde.app.domain.Client;
import com.asde.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IClientService{

    /* ~    AUTOWIRED
    --------------------------------------------------- */
    private ClientRepository clientRepo;
    @Autowired
    public ClientService(ClientRepository clientRepo){ this.clientRepo = clientRepo; }


    /* ~    METHODS
    --------------------------------------------------- */
    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientById(Integer idClient) {
        return clientRepo.findById(idClient).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientByRfc(String rfc) {
        return clientRepo.findClientByRfc(rfc).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveClient(Client client) {
        clientRepo.save(client);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteClientById(Integer idClient) {
        clientRepo.deleteById(idClient);
    }
} // end of service to client
