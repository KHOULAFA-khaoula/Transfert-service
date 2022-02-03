package com.ensa.transfertservice.proxies;

import com.ensa.transfertservice.bean.Agent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="AGENT-SERVICE", url="http://localhost:4000/api/agents" )
public interface MicroServiceAgentProxy {
    @GetMapping("/getAll")
    ResponseEntity<?> getAgents();
    @GetMapping("/get/{id}")
    Agent getAgentsById(@PathVariable(name = "id") Long agentId);

}
