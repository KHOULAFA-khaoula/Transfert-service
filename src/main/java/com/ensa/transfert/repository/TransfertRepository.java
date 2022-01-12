package com.ensa.transfert.repository;

import com.ensa.transfert.entity.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {

    Transfert findByTransfertId(Long tranfertId);
}
