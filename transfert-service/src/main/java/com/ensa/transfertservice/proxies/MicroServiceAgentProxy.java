package com.ensa.transfertservice.proxies;

import com.ensa.transfertservice.bean.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="AGENT-SERVICE", url="http://localhost:4000/api/agents" )
public interface MicroServiceAgentProxy {
    @GetMapping("/getAll")
    ResponseEntity<?> getAgents();
    @GetMapping("/get/{id}")
    Agent getAgentsById(@PathVariable(name = "id") Long agentId);
    @PutMapping("/updateBalance/{id}/{amount}/{operation}")
    public ResponseEntity<?> updateBalance(@PathVariable(name = "id") Long agentId, @PathVariable(name = "amount") Double amount, @PathVariable(name = "operation") String operation);


}
