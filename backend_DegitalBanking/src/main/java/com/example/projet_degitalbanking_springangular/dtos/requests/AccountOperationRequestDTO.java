package com.example.projet_degitalbanking_springangular.dtos.requests;


import lombok.Data;


@Data
public class AccountOperationRequestDTO {
    private Double amount;
    private String idBankAccountSource;
    private String idBankAccountDestination;
    private String description;
}
