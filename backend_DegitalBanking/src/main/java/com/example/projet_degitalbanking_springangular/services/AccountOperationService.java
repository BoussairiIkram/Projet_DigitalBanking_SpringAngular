package com.example.projet_degitalbanking_springangular.services;

import com.example.projet_degitalbanking_springangular.Exception.AccountOperationNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.BalanceNotSufficientException;
import com.example.projet_degitalbanking_springangular.Exception.BankAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.AccountOperationRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.AccountOperationRespenseDTO;

import java.util.List;

public interface AccountOperationService {

    void Debit(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException;
    //Retrait
    void Credit(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void Transfet(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException;

    List<AccountOperationRespenseDTO> getAccountHistory(String id) throws BankAccountNotFoundException;
    AccountOperationRespenseDTO getAccountOperation(Long id) throws AccountOperationNotFoundException;

    Boolean deleteAccountOperation(Long id) throws AccountOperationNotFoundException;
}
