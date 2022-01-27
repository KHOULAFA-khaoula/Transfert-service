package com.ensa.transfertservice.controller;


import com.ensa.transfertservice.enums.EtatTransfert;
import com.ensa.transfertservice.proxies.MicroServiceClientProxy;
import com.ensa.transfertservice.service.TransfertService;
import com.ensa.transfertservice.entity.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/transferts")
@Slf4j
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    MicroServiceClientProxy mclientProxy;
    @GetMapping("/all")
    public Iterable<Transfert> readAllTransferts() {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.findAll();
    }

    @PostMapping("/new")
    public Transfert saveTransfert(@RequestBody Transfert transfert) {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.save(transfert);
    }

    @GetMapping("/{id}")
    public Transfert findTransfertById(@PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.findByTransfertId(transfertId);
    }

    @PutMapping("/{id}")
    public Transfert updateTransfert( @PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        Transfert transfert = transfertService.findByTransfertId(transfertId);

        return transfertService.update(transfert);
    }
   /* @DeleteMapping("/{id}")
    public Transfert deleteTransfert( @PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.saveTransfert(transfert);
    }*/

    @GetMapping("/clientsTesting")
    public String testerMicroService(Model model) {
        ResponseEntity<?> clients = mclientProxy.findAll();

        model.addAttribute("clients", clients);
        return model.toString();
    }


}
