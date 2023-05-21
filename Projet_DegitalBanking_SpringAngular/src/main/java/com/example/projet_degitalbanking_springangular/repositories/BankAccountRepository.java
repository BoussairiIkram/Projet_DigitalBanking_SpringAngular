package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
