package com.example.projet_degitalbanking_springangular.dtos.mappers;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.CurrentAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CurrentAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.CurrentAccount;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component @AllArgsConstructor
public class CurrentAccountMapper {

    private CustomerRepository customerRepository;
    private  CustomerMapper customerMapper;
    public CurrentAccount fromCurrentAccountRequestDTO(CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountRequestDTO, currentAccount);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setId(UUID.randomUUID().toString());
        if(currentAccountRequestDTO.getCustomerId()!=null){
            Customer customer = customerRepository.findById(currentAccountRequestDTO.getCustomerId()).orElseThrow(()->new CustomerNotFoundException(currentAccountRequestDTO.getCustomerId()));
            if(customer!=null) currentAccount.setCustomer(customer);
        }
        return  currentAccount;
    }

    public CurrentAccountRespenseDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountRespenseDTO currentAccountRespenseDTO = new CurrentAccountRespenseDTO();
        BeanUtils.copyProperties(currentAccount,currentAccountRespenseDTO);
        if(currentAccount.getCustomer()!=null) currentAccountRespenseDTO.setCustomer(customerMapper.fromCustomer(currentAccount.getCustomer()));
        currentAccountRespenseDTO.setType(currentAccount.getClass().getSimpleName());
        currentAccountRespenseDTO.setStatus(currentAccount.getStatus().toString());
        return  currentAccountRespenseDTO;
    }
}
