package com.ensa.transfert.service;


import com.ensa.transfert.entity.Transfert;
import com.ensa.transfert.repository.TransfertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;


    public Transfert saveTransfert(Transfert transfert) {
       // log.info("Inside saveDepartment of DepartmentService");
        return transfertRepository.save(transfert);
    }

    public Transfert findTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
        return transfertRepository.findByTransfertId(transfertId);
    }
/*
    public Transfert updateTransfertById(Long transfertId) {
        //log.info("Inside saveDepartment of DepartmentService");
      //  return transfertRepository.update(transfertId);
    }*/

}
