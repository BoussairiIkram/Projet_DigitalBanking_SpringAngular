package com.example.projet_degitalbanking_springangular.entities;

import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation;
    private String description;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private OperationType opType;


    @ManyToOne @JoinColumn(name = "idBankAccount")
    private BankAccount bankAccount;

}
