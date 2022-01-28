package com.example.clientservice.repository;

import com.example.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByPhone(String phone);

}
