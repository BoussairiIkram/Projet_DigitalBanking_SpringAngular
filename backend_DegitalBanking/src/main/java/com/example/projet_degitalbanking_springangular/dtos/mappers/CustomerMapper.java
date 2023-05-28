package com.example.projet_degitalbanking_springangular.dtos.mappers;

import com.example.projet_degitalbanking_springangular.dtos.requests.CustomerRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CustomerRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;



@Component
public class CustomerMapper {
    public Customer fromCustomerRequestDTO(CustomerRequestDTO customerRequestDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDTO, customer);
        return  customer;
    }

    public CustomerRespenseDTO fromCustomer(Customer customer){
        CustomerRespenseDTO customerRespenseDTO = new CustomerRespenseDTO();
        BeanUtils.copyProperties(customer,customerRespenseDTO);
        return  customerRespenseDTO;
    }
}
