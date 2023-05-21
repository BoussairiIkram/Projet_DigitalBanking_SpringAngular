package com.example.projet_degitalbanking_springangular.repositories;

import com.example.projet_degitalbanking_springangular.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
