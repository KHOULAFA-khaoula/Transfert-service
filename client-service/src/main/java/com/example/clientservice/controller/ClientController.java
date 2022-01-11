package com.example.clientservice.controller;

import com.example.clientservice.convert.ClientConverter;
import com.example.clientservice.dto.ClientDTO;
import com.example.clientservice.exception.ClientDuplicatedException;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.model.Client;
import com.example.clientservice.service.ClientService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@RestController
@RequestMapping("/clients")
@Data
@Slf4j
public class ClientController {
    final ClientService clientService;
    final ClientConverter clientConverter;
    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody ClientDTO clientDTO) throws Exception {
        if (clientDTO == null)
            return ResponseEntity.badRequest().body("The provided movie is not valid");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConverter.convertToDTO(clientService.save(clientConverter.convertToDM(clientDTO))));
    }
    @PutMapping("/")
    public ResponseEntity<?> update(@Valid @RequestBody ClientDTO clientDTO) throws Exception {
        if (clientDTO == null)
            return ResponseEntity.badRequest().body("The provided movie is not valid");
        return ResponseEntity
                .ok()
                .body(clientConverter.convertToDTO(clientService.update(clientConverter.convertToDM(clientDTO))));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ClientNotFoundException {
        if (id == null)
            return ResponseEntity.badRequest().body("The provided client's id is not valid");
        return ResponseEntity.ok().body("Client [" + clientService.delete(id) + "] deleted successfully.");
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(clientService.findAll());
    }
}
