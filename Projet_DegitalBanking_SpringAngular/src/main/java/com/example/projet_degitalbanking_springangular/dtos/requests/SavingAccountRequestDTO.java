package com.example.projet_degitalbanking_springangular.dtos.requests;

import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import lombok.Data;


@Data
public class SavingAccountRequestDTO {
    private String currency;
    private AccountStatus status;
    private Long customerId;
    private Double balance;
    private Double interestRate; //taut d'interet
   // private AccountOperationRequestDTO[] accountOperationsRequestDTO;
}
