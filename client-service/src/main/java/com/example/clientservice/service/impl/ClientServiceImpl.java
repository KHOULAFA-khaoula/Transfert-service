package com.example.clientservice.service.impl;

import com.example.clientservice.exception.ClientDuplicatedException;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.model.Client;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@Service
@Data
public class ClientServiceImpl implements ClientService {
    final ClientRepository clientRepository;

    @Override
    public Client save(Client client) throws ClientDuplicatedException {
        Client clientfromDB = clientRepository.findById(client.getId()).orElse(null);
        if(clientfromDB != null ){
            throw new ClientDuplicatedException(client.getId());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) throws ClientNotFoundException {
        Client clientfromDB = clientRepository.findById(client.getId()).orElse(null);
        if(clientfromDB != null ){
            throw new ClientNotFoundException(client.getId());
        }
        return clientRepository.save(client);
    }

    @Override
    public Long delete(Long id) throws ClientNotFoundException {
        Client clientfromDB = clientRepository.findById(id).orElse(null);
        if (clientfromDB == null )
            throw new ClientNotFoundException(id);
        clientRepository.delete(clientfromDB);
        return id;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}