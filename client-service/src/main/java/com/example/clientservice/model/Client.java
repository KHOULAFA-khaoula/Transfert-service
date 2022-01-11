package com.example.clientservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private float balance;
    private Date created_at;
    /*private Long id;
    private String firstName;
    private String lastName;
    private Date DateBirth;
    private String TypeID;
    private String idNumber;
    private Date idValidity;
    private String CIN;
    private Date dateOfBirth;
    private String Country;
    private String nationalityCountry;
    private String countryAddress;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;*/


}

