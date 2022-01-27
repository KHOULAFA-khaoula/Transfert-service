package com.ensa.transfertservice.bean;

import lombok.Data;

import java.util.Date;
@Data
public class Client {

    private  Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private float balance;
    private Date created_at;
}
