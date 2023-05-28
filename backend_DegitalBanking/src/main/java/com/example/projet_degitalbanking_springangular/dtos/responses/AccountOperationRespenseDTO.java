package com.example.projet_degitalbanking_springangular.dtos.responses;

import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationRespenseDTO {
    private Long id;
    private Date dateOperation;
    private Double amount;
    private OperationType opType;
    private String description;
    private String idBankAccount;
}
