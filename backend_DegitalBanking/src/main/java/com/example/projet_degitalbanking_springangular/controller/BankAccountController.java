package com.example.projet_degitalbanking_springangular.controller;

import com.example.projet_degitalbanking_springangular.Exception.BankAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CurrentAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.responses.BankAcountRespenseDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CurrentAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
@CrossOrigin("*")
public class BankAccountController {
    private BankAccountService bankAccountService;
    @GetMapping("/all")
    public List<BankAcountRespenseDTO> getAllAccounts(){
        return bankAccountService.getAccounts();
    }

    @GetMapping("/{id}")
    public BankAcountRespenseDTO getAccount(@PathVariable String id) throws  BankAccountNotFoundException {
        if(id!=null) return bankAccountService.getAccount(id);
        return null;
    }
    @GetMapping("/customer/{id}")
    public List<BankAcountRespenseDTO> getAccountsCustomer(@PathVariable Long id) throws  CustomerNotFoundException {
        if(id!=null) return bankAccountService.getAccountsCustomer(id);
        return null;
    }
}
