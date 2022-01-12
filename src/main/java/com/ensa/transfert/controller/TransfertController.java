package com.ensa.transfert.controller;


import com.ensa.transfert.service.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TransfertController {

    @Autowired
    private TransfertService transfertService;
}
