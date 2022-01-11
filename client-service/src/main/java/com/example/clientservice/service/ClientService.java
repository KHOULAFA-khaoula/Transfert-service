package com.example.clientservice.service;

import com.example.clientservice.exception.ClientDuplicatedException;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.model.Client;

import java.util.List;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
public interface ClientService {
    Client save(Client client) throws ClientDuplicatedException;
    Client update(Client client) throws ClientNotFoundException;
    Long delete(Long id) throws ClientNotFoundException;
    List<Client> findAll();


}
