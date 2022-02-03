package com.ensa.transfertservice.bean;

import lombok.Data;

@Data
public class Agent {
    private Long agentId;

    private String email;
    private String password;
    private double solde;
}
