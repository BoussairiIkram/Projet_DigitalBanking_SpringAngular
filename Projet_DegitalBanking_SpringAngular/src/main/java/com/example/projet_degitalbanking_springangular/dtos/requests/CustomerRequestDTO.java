package com.example.projet_degitalbanking_springangular.dtos.requests;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerRequestDTO {
    private String nom;
    private Date dateNaissance;
    private String email;
   // private BankAcountRequestDTO[] bankAcountRequestDTOS;
}
