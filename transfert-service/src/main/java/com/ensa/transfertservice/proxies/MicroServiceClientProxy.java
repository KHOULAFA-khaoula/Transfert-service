package com.ensa.transfertservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CLIENT-SERVICE", url="localhost:9003/clients" )
public interface MicroServiceClientProxy {
    @GetMapping("/")
    ResponseEntity<?> findAll();
}
