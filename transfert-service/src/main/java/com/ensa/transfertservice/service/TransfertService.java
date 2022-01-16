package com.ensa.transfertservice.service;


import com.ensa.transfertservice.entity.Transfert;
import com.ensa.transfertservice.repository.TransfertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;


    public Transfert saveTransfert(Transfert transfert) {
       // si la sqource est pooint de vente
        if (transfert.getSourceTransfert().equals("POINT_DE_VENTE")){

            if (transfert.getModeTransfert().equals("DEBIT_COMPTE")) {
                /****** Verification du montant ******/
                 if(transfert.getMontantTransfert() > 2000) {
                     System.out.println("Montant dépassée");
                 }
                 else if (transfert.getMontantTransfert() > transfert.getBalanceDonneur()){
                     System.out.println("Solde Insuffisant");
                 }
                 /**************************/

                /****** Notifié ou pas ******/
                 if(transfert.isNotified() == true) {
                     // Faire le traitement de ntofication pour notiier le bénificiare avec Reference du transfert + le donneur losrque le tranfert est servie
                     System.out.println("Vous êtes notifiés");
                 }
                /**************************/

                /****** Type de frais  ******/
                 if(transfert.getTypeFraisTransfert().equals("CHARGE_DONNEUR_ORDRE")) {
                     // les frais de transfert ont été ajouté au donneur ,le pirx est a 1000
                     Double montantSaisie = transfert.getMontantTransfert();
                     transfert.setMontantOperation(montantSaisie+1000);
                     transfert.setMontantTransfert(montantSaisie);

                 }else if (transfert.getTypeFraisTransfert().equals("CHARGE_BENEFICIARE")){
                     // les frais de transfert ont été soustré du bénificiaire ,le pirx est a 1000
                     Double montantSaisie = transfert.getMontantTransfert();
                     transfert.setMontantOperation(montantSaisie);
                     transfert.setMontantTransfert(montantSaisie-1000);
                 }
                 else {
                     //les frais sont partagés
                     Double montantSaisie = transfert.getMontantTransfert();
                     transfert.setMontantOperation(montantSaisie+500);
                     transfert.setMontantTransfert(montantSaisie-500);

                 }
                /**************************/
            }
            else {

            }
        }
        //si la source est wallet
        else {

        }
        return transfertRepository.save(transfert);
    }

    public Transfert findTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
        return transfertRepository.findByTransfertId(transfertId);
    }

   /* void Transfert deleteTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
       return transfertRepository.deleteById(transfertId);
    }*/

}
