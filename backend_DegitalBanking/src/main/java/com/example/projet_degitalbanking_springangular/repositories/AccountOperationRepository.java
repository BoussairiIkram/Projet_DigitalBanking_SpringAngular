package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

    @Query("SELECT e FROM AccountOperation e JOIN FETCH e.bankAccount c WHERE c.id = :cleEtrangere ORDER BY e.dateOperation DESC")
    Page<AccountOperation> findByCleEtrangereOrderByDateCreationDesc(String cleEtrangere, Pageable pegeable);
}
