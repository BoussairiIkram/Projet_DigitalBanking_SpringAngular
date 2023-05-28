package com.example.projet_degitalbanking_springangular.services.servicesImp;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.mappers.CustomerMapper;
import com.example.projet_degitalbanking_springangular.dtos.requests.CustomerRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CustomerRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import com.example.projet_degitalbanking_springangular.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerRespenseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        if(customerRequestDTO!=null){
            Customer customer = customerMapper.fromCustomerRequestDTO(customerRequestDTO);
            customerRepository.save(customer);
            return customerMapper.fromCustomer(customer);
        }
        return null;
    }

    @Override
    public CustomerRespenseDTO updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException {
        if(id!=null && customerRequestDTO!=null){
            Customer customer = customerRepository.findById(id).orElse(null);
            if(customer!=null){
                if(customerRequestDTO.getNom()!=null) customer.setNom(customerRequestDTO.getNom());
                if(customerRequestDTO.getEmail()!=null) customer.setEmail(customerRequestDTO.getEmail());
                if(customerRequestDTO.getDateNaissance()!=null) customer.setDateNaissance(customerRequestDTO.getDateNaissance());
                customerRepository.save(customer);
                return customerMapper.fromCustomer(customer);
            } throw new CustomerNotFoundException(id);
        }
        return null;
    }

    @Override
    public CustomerRespenseDTO getCustomer(Long id) throws CustomerNotFoundException {
        if(id!=null){
            Customer customer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
            return customerMapper.fromCustomer(customer);
        }
        return null;
    }

    @Override
    public List<CustomerRespenseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerRespenseDTO> customerRespenseDTOS = customers.stream()
                .map(customer -> customerMapper.fromCustomer(customer))
                .collect(Collectors.toList());

        return customerRespenseDTOS;
    }

    @Override
    public List<CustomerRespenseDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.findByNomContains(keyword);
        if(customers!=null){
            List<CustomerRespenseDTO> customerRespenseDTOS = customers.stream()
                    .map(customer -> customerMapper.fromCustomer(customer))
                    .collect(Collectors.toList());
            return customerRespenseDTOS;
        }
        return null;
    }

    @Override
    public Boolean deleteCustomer(Long id) throws CustomerNotFoundException {
        if(id!=null){
            Customer customer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
            if(customer!=null) {
                customerRepository.delete(customer);
                return true;
            }

        }
        return false;
    }
}
