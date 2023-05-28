package com.example.projet_degitalbanking_springangular.entities;


import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="TYPE",length = 4)
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private String currency;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne @JoinColumn(name = "idCustomer")
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;


}
