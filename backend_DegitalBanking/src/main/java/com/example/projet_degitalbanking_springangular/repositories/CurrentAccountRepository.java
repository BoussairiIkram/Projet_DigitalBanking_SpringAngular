package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount,String> {
}
