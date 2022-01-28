package com.ensa.transfertservice.service.impl;

import com.ensa.transfertservice.bean.Client;
import com.ensa.transfertservice.entity.Transfert;
import com.ensa.transfertservice.enums.*;
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
        int frais_transfert = 100;
        if(transfert.getTypeFraisTransfert() == TypeFraisTransfert.CHARGE_DONNEUR_ORDRE) {
            // les frais de transfert ont été ajouté au donneur ,le pirx est a 1000
            Double montantSaisie = transfert.getMontantTransfert();
            transfert.setMontantOperation(montantSaisie+frais_transfert);
            transfert.setMontantTransfert(montantSaisie);

        }else if (transfert.getTypeFraisTransfert() == TypeFraisTransfert.CHARGE_BENEFICIARE){
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

    private boolean veriferCanal(Transfert transfert) {
        boolean output = true;
        if (transfert.getCanalTransfert() == CanalTransferts.GAB) {
            if(!transfert.getClientBeneficiaireTele().equals(transfert.getClientDonneurTele())){
                output = false;
            }
        }

        return output;
    }

    private boolean verfierMontant(Transfert transfert){
        boolean output = false;
        int plafond_mensuel;
        int plafond_annuel;
        if(transfert.getCanalTransfert() == CanalTransferts.GAB) {
            plafond_annuel=20000;
            plafond_mensuel=2000;
            if (transfert.getMontantTransfert() < plafond_mensuel && transfert.getMontantTransfert() < transfert.getBalanceDonneur()) {
                output = true;
            }
        }
        else if( transfert.getModeTransfert() == ModeTransfert.DEBIT_COMPTE) {
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
        boolean isCanalVerified = veriferCanal(transfert);
        boolean isNotified = choisirNotif(transfert);
        Transfert output =null;
        if(isMontantVerified && isCanalVerified){
            calculerFrais(transfert);
            transfert.setEtatTransfert(EtatTransfert.ASERVIR);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
            output = this.transfertRepository.save(transfert);
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return output;
    }
    private Transfert transfertPointVenteEspeces(Transfert transfert) {
        boolean isMontantVerified = verfierMontant(transfert);
        boolean isNotified = choisirNotif(transfert);
        Transfert output = null;
        if(isMontantVerified){
            calculerFrais(transfert);
            transfert.setEtatTransfert(EtatTransfert.ASERVIR);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
            output = this.transfertRepository.save(transfert);
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return output;
    }
    private Transfert transfertWalletDebit(Transfert transfert){

        boolean isMontantVerified = verfierMontant(transfert);
        boolean isCanalVerified = veriferCanal(transfert);
        boolean isNotified = choisirNotif(transfert);
        Transfert output = null;
        if(isMontantVerified && isCanalVerified){
            calculerFrais(transfert);
            transfert.setEtatTransfert(EtatTransfert.ASERVIR);
            if(isNotified) {
                System.out.println("Twilio ==> appelc au microservice notification");
            }
            output = this.transfertRepository.save(transfert);
        }
        else {
            /***Excetption**/
            System.out.println("Exception : Le montant dépasser veuiller resaisir à nouveau ou abondonner");
        }
        return output;
    }

    @Override
    public Transfert save(Transfert transfert, Client donneur) {

        Transfert output ;
        transfert.setBalanceDonneur((double)donneur.getBalance());
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
    public Transfert update(Transfert transfert, EtatTransfert etatTransfert) {

        if(transfert.getCanalTransfert() == CanalTransferts.POINT_DE_VENTE){
            if(transfert.getModeRecuperation() == ModeRecuperation.ESPECE)   {
                //
            }
            else {

            }
        }
        // si le canal est GAB
        else {

        }

        transfert.setEtatTransfert(etatTransfert);
        //if (transfert.get)
        return transfertRepository.save(transfert);
    }

    @Override
    public Long delete(Long id) {

        return null;
    }
    @Override
    public Transfert findByReferenceCode(String reference_code){
        return transfertRepository.findByReferenceCode(reference_code);
    }
   /* void Transfert deleteTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
       return transfertRepository.deleteById(transfertId);
    }*/

}
