package com.ensa.transfertservice.service;


import com.ensa.transfertservice.bean.Client;
import com.ensa.transfertservice.entity.Transfert;
import com.ensa.transfertservice.enums.EtatTransfert;
import com.ensa.transfertservice.enums.ModeTransfert;
import com.ensa.transfertservice.enums.SourceTransferts;
import com.ensa.transfertservice.enums.TypeFraisTransfert;
import com.ensa.transfertservice.repository.TransfertRepository;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransfertService {

    Transfert save(Transfert transfert, Client donneur) ;
    Transfert update(Transfert transfert, EtatTransfert etatTransfert);
    Transfert findByTransfertId(Long transfertId);
    Long delete(Long id) ;
    List<Transfert> findAll();
    Transfert findByReferenceCode(String referenceCode);
    List<Transfert> findByClientDonneurTele(String clientDonneurTele);
    List<Transfert> findByAgentId(Long agentId);

}
