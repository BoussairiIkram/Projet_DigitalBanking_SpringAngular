package com.example.projet_degitalbanking_springangular.dtos.responses;

import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class SavingAccountRespenseDTO extends BankAcountRespenseDTO {
    private String id;
    private Double balance;
    private String status;
    private Date createdAt;
    private String currency;
    private CustomerRespenseDTO customer;
    private Double interestRate; //taut d'interet

   // private AccountOperationRespenseDTO[] accountOperationRespenseDTOS;
}
