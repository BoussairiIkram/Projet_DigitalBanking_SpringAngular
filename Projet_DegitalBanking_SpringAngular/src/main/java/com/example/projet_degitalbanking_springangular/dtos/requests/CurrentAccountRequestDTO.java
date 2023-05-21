package com.example.projet_degitalbanking_springangular.dtos.requests;

import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import lombok.Data;


@Data
public class CurrentAccountRequestDTO {
    private String currency;
    private AccountStatus status;
    private Long customerId;
    private Double overDraft;
    private Double balance;
    // private AccountOperationRequestDTO[] accountOperationsRequestDTO;

}
