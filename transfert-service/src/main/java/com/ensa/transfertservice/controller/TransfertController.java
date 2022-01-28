package com.ensa.transfertservice.controller;


import com.ensa.transfertservice.bean.Client;
import com.ensa.transfertservice.enums.EtatTransfert;
import com.ensa.transfertservice.enums.ModeTransfert;
import com.ensa.transfertservice.enums.SourceTransferts;
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
    @Autowired
    MicroServiceClientProxy mclientProxy;

    @GetMapping("/all")
    public Iterable<Transfert> readAllTransferts() {
        //log.info("Inside saveTransfert method of TransfertController");
        return  transfertService.findAll();
    }

    @PostMapping("/emettre")
    public ResponseEntity<?> saveTransfert(@RequestBody Transfert transfert) {
        String numeroDonneur = transfert.getClientDonneurTele();
        Client donneur = mclientProxy.findClientByPhone(numeroDonneur);
       if (transfertService.save(transfert,donneur) == null) {
            return  ResponseEntity.badRequest().body("Transfert non valide , veuiller saisir a nouveau !");
        }
        else {
            if((transfert.getModeTransfert() == ModeTransfert.DEBIT_COMPTE)) {

                mclientProxy.updateBalance(donneur.getId(),transfert.getMontantOperation(),"Soustraire");
            }//sinon soustraire depuis le solde de l'agent*/
            return  ResponseEntity.ok().body(transfertService.save(transfert,donneur));
        }

    }

    @GetMapping("/{id}")
    public Transfert findTransfertById(@PathVariable("id") Long transfertId) {
        //log.info("Inside findTransfertById method of TransfertController");
        return transfertService.findByTransfertId(transfertId);
    }
    // Servir  point de vente wallet
    @PutMapping("/servir/PVW/{code}")
    public ResponseEntity<?> servirTransfertPW( @PathVariable("code") String code) {
        Transfert transfert = transfertService.findByReferenceCode(code);
        if(transfert.getEtatTransfert() == EtatTransfert.ASERVIR || transfert.getEtatTransfert() == EtatTransfert.DEBLOQUÉ ) {
           String numeroBeneficiaire = transfert.getClientBeneficiaireTele();
           Client beneficiare = mclientProxy.findClientByPhone(numeroBeneficiaire);
            mclientProxy.updateBalance(beneficiare.getId(), transfert.getMontantTransfert(), "Ajouter");
           return ResponseEntity.ok().body(transfertService.update(transfert, EtatTransfert.PAYÉ));

       }
      else {
           return ResponseEntity.badRequest().body("Impossible de servir le transfert "+code +" car il est  "+transfert.getEtatTransfert().toString());
       }

    }

// Servir  point de vente espéces
   @PutMapping("/servir/PVE/{code}")
   public ResponseEntity<?> servirTransfertPE( @PathVariable("code") String code) {
       Transfert transfert = transfertService.findByReferenceCode(code);
       if(transfert.getEtatTransfert() == EtatTransfert.ASERVIR || transfert.getEtatTransfert() == EtatTransfert.DEBLOQUÉ ) {
         //traiter le solde de l'agent
           return ResponseEntity.ok().body(transfertService.update(transfert, EtatTransfert.PAYÉ));

       }
       else {
           return ResponseEntity.badRequest().body("Impossible de servir le transfert "+code +" car il est "+transfert.getEtatTransfert().toString());
       }
   }
    @GetMapping("/clientsTesting")
    public ResponseEntity<?> testerMicroService(Model model) {
        ResponseEntity<?> clients = mclientProxy.findAll();

        model.addAttribute("clients", clients);
        return clients;
    }
    //Recuperer le transfert par reference
    @GetMapping("/reference/{code}")
    public ResponseEntity<?> findByReferenceCode(@PathVariable("code") String reference ){
       return ResponseEntity.ok().body(transfertService.findByReferenceCode(reference));
    }


}
