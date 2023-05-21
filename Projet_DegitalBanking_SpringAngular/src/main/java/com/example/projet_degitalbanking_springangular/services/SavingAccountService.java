package com.example.projet_degitalbanking_springangular.services;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.SavingAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.SavingAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;

import java.util.List;

public interface SavingAccountService {
    SavingAccountRespenseDTO createSavingAccount(SavingAccountRequestDTO savingAccountRequestDTO) throws CustomerNotFoundException;
    SavingAccountRespenseDTO updateSavingAccount(String id,SavingAccountRequestDTO savingAccountRequestDTO) throws SavingAccountNotFoundException, CustomerNotFoundException;
    SavingAccountRespenseDTO getSavingAccount(String id) throws SavingAccountNotFoundException;
    List<SavingAccountRespenseDTO> getAllSavingAccounts();
    Boolean deleteSavingAccount(String id) throws SavingAccountNotFoundException;
}
