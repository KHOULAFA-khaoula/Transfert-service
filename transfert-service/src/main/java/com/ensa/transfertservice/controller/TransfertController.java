package com.ensa.transfertservice.controller;


import com.ensa.transfertservice.service.TransfertService;
import com.ensa.transfertservice.entity.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/transferts")
@Slf4j
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @GetMapping("/all")
    public Iterable<Transfert> readAllTransferts() {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.findAll();
    }

    @PostMapping("/")
    public Transfert saveTransfert(@RequestBody Transfert transfert) {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.save(transfert);
    }

    @GetMapping("/{id}")
    public Transfert findTransfertById(@PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.findByTransfertId(transfertId);
    }
/*
    @PutMapping("/transfert/{id}")
    public Transfert updateTransfert( @PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        Transfert transfert = transfertService.findTransfertById(transfertId);
        return transfertService.saveTransfert(transfert);
    }*/
   /* @DeleteMapping("/{id}")
    public Transfert deleteTransfert( @PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.saveTransfert(transfert);
    }*/


}
