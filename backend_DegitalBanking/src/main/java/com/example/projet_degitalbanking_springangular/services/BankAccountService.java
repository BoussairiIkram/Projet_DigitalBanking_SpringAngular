package com.example.projet_degitalbanking_springangular.services;

import com.example.projet_degitalbanking_springangular.Exception.BankAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.responses.BankAcountRespenseDTO;

import java.util.List;

public interface BankAccountService {
    BankAcountRespenseDTO getAccount(String id) throws BankAccountNotFoundException;
    List<BankAcountRespenseDTO> getAccountsCustomer(Long id) throws CustomerNotFoundException;
    List<BankAcountRespenseDTO> getAccounts();
}
