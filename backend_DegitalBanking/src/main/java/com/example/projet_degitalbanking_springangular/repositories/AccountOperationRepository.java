package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.AccountOperation;
import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

    @Query("SELECT e FROM AccountOperation e JOIN FETCH e.bankAccount c WHERE c.id = :cleEtrangere ORDER BY e.dateOperation DESC")
    List<AccountOperation> findByCleEtrangereOrderByDateCreationDesc(String cleEtrangere);
}
