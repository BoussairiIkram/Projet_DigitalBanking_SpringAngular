package com.example.projet_degitalbanking_springangular.dtos.responses;

import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentAccountRespenseDTO {
    private String id;
    private Date createdAt;
    private String currency;
    private AccountStatus status;
    private CustomerRespenseDTO customer;
    private Double overDraft;
    private AccountOperationRespenseDTO[] accountOperationRespenseDTOS;
    private Double balance;
}
