package com.example.projet_degitalbanking_springangular.dtos.mappers;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.SavingAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.entities.SavingAccount;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component @AllArgsConstructor
public class SavingAccountMapper {

    private CustomerRepository customerRepository;
    private  CustomerMapper customerMapper;
    public SavingAccount fromSavingAccountRequestDTO(SavingAccountRequestDTO savingAccountRequestDTO) throws CustomerNotFoundException {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountRequestDTO, savingAccount);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setId(UUID.randomUUID().toString());
        if(savingAccountRequestDTO.getCustomerId()!=null){
            Customer customer = customerRepository.findById(savingAccountRequestDTO.getCustomerId()).orElseThrow(()->new CustomerNotFoundException(savingAccountRequestDTO.getCustomerId()));
            if(customer!=null) savingAccount.setCustomer(customer);
        }
        return  savingAccount;
    }

    public SavingAccountRespenseDTO fromSavingAccount(SavingAccount savingAccount){
        SavingAccountRespenseDTO savingAccountRespenseDTO = new SavingAccountRespenseDTO();
        BeanUtils.copyProperties(savingAccount,savingAccountRespenseDTO);
        if(savingAccount.getCustomer()!=null) savingAccountRespenseDTO.setCustomer(customerMapper.fromCustomer(savingAccount.getCustomer()));
        savingAccountRespenseDTO.setType(savingAccount.getClass().getSimpleName());
        savingAccountRespenseDTO.setStatus(savingAccount.getStatus().toString());
        return  savingAccountRespenseDTO;
    }
}
