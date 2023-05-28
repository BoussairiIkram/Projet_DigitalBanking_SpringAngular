package com.example.projet_degitalbanking_springangular.controller;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.CustomerRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CustomerRespenseDTO;
import com.example.projet_degitalbanking_springangular.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerRespenseDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerRespenseDTO getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        if(id!=null) return customerService.getCustomer(id);
        return null;
    }

    @GetMapping("/search")
    public  List<CustomerRespenseDTO> searchCustomer(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return customerService.searchCustomers(keyword);
    }

    @PostMapping("/add")
    public CustomerRespenseDTO createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        if(customerRequestDTO!=null) return customerService.createCustomer( customerRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public CustomerRespenseDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException {
        if(customerRequestDTO!=null && id!=null) return customerService.updateCustomer(id,customerRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEtudiant(@PathVariable Long id) throws CustomerNotFoundException {
        if( id!=null) return customerService.deleteCustomer(id);
        return false;
    }
}
