package com.ensa.transfertservice.entity;


import com.ensa.transfertservice.enums.*;
import lombok.*;
import java.util.Date;
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

    private String clientBeneficiaireTele;

    // A remplacer avec la table Client
    @Column
    private Double balanceDonneur;
   // A cosultet la ta table agent

    @Column
    private Double soldeAgent;

    @Column
    private Double montantOperation;


    @Column
    private Double montantTransfert;

    @Column
    private boolean isNotified;


    @Column
    @Enumerated(EnumType.STRING)
    private MotifTransfert motifTranfert;

    @Column
    @Enumerated(EnumType.STRING)
    private EtatTransfert etatTransfert;



    @Column
    @Enumerated(EnumType.STRING)
    private ModeTransfert modeTransfert;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeFraisTransfert typeFraisTransfert;


    @Column
    @Enumerated(EnumType.STRING)
    private SourceTransferts sourceTransfert;

    @Column
    @Enumerated(EnumType.STRING)
    private CanalTransferts canalTransfert;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;



}
