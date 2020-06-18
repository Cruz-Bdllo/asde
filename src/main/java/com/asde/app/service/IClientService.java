package com.asde.app.service;

import com.asde.app.domain.Client;

import java.util.List;

public interface IClientService {

    List<Client> getAllClients();

    Client getClientById(Integer idClient);

    Client getClientByRfc(String rfc);

    void saveClient(Client client);

    void deleteClientById(Integer idClient);

}
