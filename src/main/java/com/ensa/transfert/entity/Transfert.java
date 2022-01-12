package com.ensa.transfert.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String clientDonneur;
    private String[] clientBeneficiaire;
    private String etatTransfert;
    private String montantTransfert;
    private String motifTranfert;
    private boolean isNotified;
    private String sourceTransfert;


}
