package com.ensa.transfertservice.entity;


import com.ensa.transfertservice.enums.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tranfertId;
    //private String référence ; genrerr des codes uniques pr chaque tranfert commencant par EDP837
    /**A considérer temporairement comme clients */
    @Column
    private String clientDonneurTele;

    @Column
    private String[] clientBeneficiaireTele;

    // A remplacer avec la table Client
    @Column
    private Double balanceDonneur;

    @Column
    private EtatTransfert etatTransfert;

    @Column
    private ModeTransfert modeTransfert;

    @Column
    private Double montantOperation;


    @Column
    private Double montantTransfert;

    @Column
    private String motifTranfert;

    @Column
    private TypeFraisTransfert typeFraisTransfert;

    @Column
    private boolean isNotified;

    public TypeFraisTransfert getTypeFraisTransfert() {
        return typeFraisTransfert;
    }

    public void setTypeFraisTransfert(TypeFraisTransfert typeFraisTransfert) {
        this.typeFraisTransfert = typeFraisTransfert;
    }



    @Column
    private SourceTransferts sourceTransfert;

    @Column
    private CanalTransferts canalTransfert;

    @Column
    private LocalDateTime date;


    public Long getTranfertId() {
        return tranfertId;
    }

    public void setTranfertId(Long tranfertId) {
        this.tranfertId = tranfertId;
    }

    public String getClientDonneurTele() {
        return clientDonneurTele;
    }

    public void setClientDonneurTele(String clientDonneurTele) {
        this.clientDonneurTele = clientDonneurTele;
    }


    public EtatTransfert getEtatTransfert() {
        return etatTransfert;
    }

    public void setEtatTransfert(EtatTransfert etatTransfert) {
        this.etatTransfert = etatTransfert;
    }

    public Double getMontantTransfert() {
        return montantTransfert;
    }

    public void setMontantTransfert(Double montantTransfert) {
        this.montantTransfert = montantTransfert;
    }

    public String getMotifTranfert() {
        return motifTranfert;
    }

    public void setMotifTranfert(String motifTranfert) {
        this.motifTranfert = motifTranfert;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }

    public SourceTransferts getSourceTransfert() {
        return sourceTransfert;
    }

    public void setSourceTransfert(SourceTransferts sourceTransfert) {
        this.sourceTransfert = sourceTransfert;
    }

    public CanalTransferts getCanalTransfert() {
        return canalTransfert;
    }

    public void setCanalTransfert(CanalTransferts canalTransfert) {
        this.canalTransfert = canalTransfert;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String[] getClientBeneficiaireTele() {
        return clientBeneficiaireTele;
    }

    public Double getBalanceDonneur() {
        return balanceDonneur;
    }

    public ModeTransfert getModeTransfert() {
        return modeTransfert;
    }

    public void setModeTransfert(ModeTransfert modeTransfert) {
        this.modeTransfert = modeTransfert;
    }

    public Double getMontantOperation() {
        return montantOperation;
    }

    public void setMontantOperation(Double montantOperation) {
        this.montantOperation = montantOperation;
    }

}
