package com.example.projet_degitalbanking_springangular.services;

import com.example.projet_degitalbanking_springangular.Exception.CurrentAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.SavingAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.CurrentAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CurrentAccountRespenseDTO;

import java.util.List;

public interface CurrentAccountService {
    CurrentAccountRespenseDTO createCurrentAccount(CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException;
    CurrentAccountRespenseDTO updateCurrentAccount(String id,CurrentAccountRequestDTO currentAccountRequestDTO) throws CurrentAccountNotFoundException, CustomerNotFoundException;
    CurrentAccountRespenseDTO getCurrentAccount(String id) throws  CurrentAccountNotFoundException;
    List<CurrentAccountRespenseDTO> getAllCurrentAccounts();

    Boolean deleteCurrentAccount(String id) throws CurrentAccountNotFoundException;
}
