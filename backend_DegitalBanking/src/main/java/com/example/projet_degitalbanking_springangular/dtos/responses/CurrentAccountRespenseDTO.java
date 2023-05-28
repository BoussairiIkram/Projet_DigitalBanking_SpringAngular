package com.example.projet_degitalbanking_springangular.dtos.responses;

import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentAccountRespenseDTO extends BankAcountRespenseDTO  {
    private String id;
    private Double balance;
    private CustomerRespenseDTO customer;
    private String status;
    private Date createdAt;
    private String currency;
    private Double overDraft;
    //private AccountOperationRespenseDTO[] accountOperationRespenseDTOS;
}
