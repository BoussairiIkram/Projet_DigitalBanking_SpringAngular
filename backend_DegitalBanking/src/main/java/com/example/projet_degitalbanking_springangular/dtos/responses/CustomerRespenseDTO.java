package com.example.projet_degitalbanking_springangular.dtos.responses;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerRespenseDTO {
    private Long id;
    private String nom;
    private Date dateNaissance;
    private String email;
   // private AccountOperationRespenseDTO[] accountOperationRespenseDTOS;
}
