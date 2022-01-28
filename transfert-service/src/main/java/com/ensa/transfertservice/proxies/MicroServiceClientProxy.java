package com.ensa.transfertservice.proxies;

import com.ensa.transfertservice.bean.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="CLIENT-SERVICE", url="localhost:9003/clients" )
public interface MicroServiceClientProxy {
    @GetMapping("/")
    ResponseEntity<?> findAll();

    @PutMapping("/updateBalance")
    public ResponseEntity<?> updateBalance(@RequestParam(name = "id") Long clientId, @RequestParam(name = "amount") Double amount, @RequestParam(name = "operation") String operation);

    @GetMapping("/phone")
    public  Client findClientByPhone(@RequestParam(name = "num") String phone);

}
