package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.AccountOperation;
import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    @Query("SELECT e FROM BankAccount e JOIN FETCH e.customer c WHERE c.id = :cleEtrangere ")
    List<BankAccount> findByCleEtrangere(Long cleEtrangere);
}
