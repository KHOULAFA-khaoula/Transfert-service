package com.ensa.transfertservice.entity;


import com.ensa.transfertservice.enums.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transfertId;
    //private String référence ; genrerr des codes uniques pr chaque tranfert commencant par EDP837
    /**A considérer temporairement comme clients */
    @Column
    private String clientDonneurTele;

    @Column
    private String[] clientBeneficiaireTele;

    // A remplacer avec la table Client
    @Column
    private Double balanceDonneur;
   // A cosultet la ta table agent

    @Column
    private Double soldeAgent;

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


    @Column
    private SourceTransferts sourceTransfert;

    @Column
    private CanalTransferts canalTransfert;

    @Column
    private LocalDateTime date;



}
