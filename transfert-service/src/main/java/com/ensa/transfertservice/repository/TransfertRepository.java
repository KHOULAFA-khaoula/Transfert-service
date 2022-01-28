package com.ensa.transfertservice.repository;

import com.ensa.transfertservice.entity.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {

    Transfert findByTransfertId(Long tranfertId);
    Transfert findByReferenceCode(String referenceCode);
}
