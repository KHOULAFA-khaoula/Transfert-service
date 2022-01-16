package com.ensa.transfertservice.controller;


import com.ensa.transfertservice.service.TransfertService;
import com.ensa.transfertservice.entity.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transferts")
@Slf4j
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @PostMapping("/")
    public Transfert saveTransfert(@RequestBody Transfert transfert) {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.saveTransfert(transfert);
    }

    @GetMapping("/{id}")
    public Transfert findTransfertById(@PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.findTransfertById(transfertId);
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
