package com.example.projet_degitalbanking_springangular.dtos.mappers;


import com.example.projet_degitalbanking_springangular.dtos.responses.AccountOperationRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.AccountOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountOperationMapper {



    public AccountOperationRespenseDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationRespenseDTO accountOperationRespenseDTO = new AccountOperationRespenseDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationRespenseDTO);
        accountOperationRespenseDTO.setIdBankAccount(accountOperation.getBankAccount().getId());
        return accountOperationRespenseDTO;
    }
}
