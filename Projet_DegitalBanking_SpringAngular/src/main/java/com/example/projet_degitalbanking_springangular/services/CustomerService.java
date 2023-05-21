package com.example.projet_degitalbanking_springangular.services;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.CustomerRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CustomerRespenseDTO;

import java.util.List;

public interface CustomerService {
    CustomerRespenseDTO createCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerRespenseDTO updateCustomer(Long id,CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException;
    CustomerRespenseDTO getCustomer(Long id) throws CustomerNotFoundException;
    List<CustomerRespenseDTO> getAllCustomers();
    Boolean deleteCustomer(Long id) throws CustomerNotFoundException;
}
