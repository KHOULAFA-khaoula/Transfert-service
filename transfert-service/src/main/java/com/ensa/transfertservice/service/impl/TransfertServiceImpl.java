package com.ensa.transfertservice.service.impl;

import com.ensa.transfertservice.entity.Transfert;
import com.ensa.transfertservice.enums.EtatTransfert;
import com.ensa.transfertservice.enums.ModeTransfert;
import com.ensa.transfertservice.enums.SourceTransferts;
import com.ensa.transfertservice.enums.TypeFraisTransfert;
import com.ensa.transfertservice.repository.TransfertRepository;
import com.ensa.transfertservice.service.TransfertService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TransfertServiceImpl implements TransfertService {


    @Autowired
    final TransfertRepository transfertRepository;

    private void calculerFrais(Transfert transfert){

        int frais_transfert = 1000;
        if(transfert.getTypeFraisTransfert().equals(TypeFraisTransfert.CHARGE_DONNEUR_ORDRE)) {
            // les frais de transfert ont été ajouté au donneur ,le pirx est a 1000
            Double montantSaisie = transfert.getMontantTransfert();
            transfert.setMontantOperation(montantSaisie+frais_transfert);
            transfert.setMontantTransfert(montantSaisie);

        }else if (transfert.getTypeFraisTransfert().equals(TypeFraisTransfert.CHARGE_BENEFICIARE)){
            // les frais de transfert ont été soustré du bénificiaire ,le pirx est a 1000
            Double montantSaisie = transfert.getMontantTransfert();
            transfert.setMontantOperation(montantSaisie);
            transfert.setMontantTransfert(montantSaisie-frais_transfert);
        }
        else {
            //les frais sont partagés
            Double montantSaisie = transfert.getMontantTransfert();
            transfert.setMontantOperation(montantSaisie+frais_transfert/2);
            transfert.setMontantTransfert(montantSaisie-frais_transfert/2);

        }
    }
    private boolean choisirNotif(Transfert transfert){
        boolean output = false;
        if(transfert.isNotified() == true) {
            // Faire le traitement de ntofication pour notiier le bénificiare avec Reference du transfert + le donneur losrque le tranfert est servie
            System.out.println("Vous êtes notifiés");
            output = true;
        }
        return output;
    }

    private ModeTransfert choisirMode(Transfert transfert ){
        return transfert.getModeTransfert();
    }
    private SourceTransferts choisirSource(Transfert transfert ){
        return transfert.getSourceTransfert();
    }

    private boolean verfierMontant(Transfert transfert){
        boolean output = false;
        int plafond_mensuel;
        int plafond_annuel;
        if (transfert.getModeTransfert() == ModeTransfert.DEBIT_COMPTE) {
            plafond_mensuel = 2000;
            /**le compteur du plafond annuel doit etre clalue à partir du  la  date du 1er tranfert emis***/
            plafond_annuel = 20000;
            if (transfert.getMontantTransfert() < plafond_mensuel && transfert.getMontantTransfert() < transfert.getBalanceDonneur()) {
                output = true;
            }
        }
        else {
            plafond_annuel = 80000;
            /**on doit ajouter la vefification du montant anuel max***/
            if (transfert.getMontantTransfert() < transfert.getSoldeAgent()) {
                output = true;
            }
        }
        return output;
    }


    private Transfert transfertPointVenteDebit(Transfert transfert){
        boolean isMontantVerified = verfierMontant(transfert);
        boolean isNotified = choisirNotif(transfert);
        if(isMontantVerified){
            calculerFrais(transfert);
            transfert.setEtatTransfert(EtatTransfert.ASERVIR);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return this.transfertRepository.save(transfert);
    }
    private Transfert transfertPointVenteEspeces(Transfert transfert){
        boolean isMontantVerified = verfierMontant(transfert);
        boolean isNotified = choisirNotif(transfert);
        if(isMontantVerified){
            calculerFrais(transfert);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return this.transfertRepository.save(transfert);
    }
    private Transfert transfertWalletDebit(Transfert transfert){

        boolean isMontantVerified = verfierMontant(transfert);
        boolean isNotified = choisirNotif(transfert);
        if(isMontantVerified){
            calculerFrais(transfert);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return this.transfertRepository.save(transfert);
    }

    @Override
    public Transfert save(Transfert transfert) {

        Transfert output;
        if (transfert.getSourceTransfert() == SourceTransferts.POINT_DE_VENTE && transfert.getModeTransfert() == ModeTransfert.DEBIT_COMPTE) {
            output = transfertPointVenteDebit(transfert);
        }
        else if(transfert.getSourceTransfert() == SourceTransferts.POINT_DE_VENTE && transfert.getModeTransfert() == ModeTransfert.ESPECE) {
            output = transfertPointVenteEspeces(transfert);
        }
        else {
            output = transfertWalletDebit(transfert);
        }
        return output;
    }


    @Override
    public Transfert findByTransfertId(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
        return transfertRepository.findByTransfertId(transfertId);
    }


    @Override
    public List<Transfert> findAll(){
        return transfertRepository.findAll();
    }
    @Override
    public Transfert update(Transfert transfert) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

   /* void Transfert deleteTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
       return transfertRepository.deleteById(transfertId);
    }*/

}
