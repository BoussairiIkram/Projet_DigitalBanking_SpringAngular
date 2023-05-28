package com.example.projet_degitalbanking_springangular.controller;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.SavingAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.SavingAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.services.SavingAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts/savingAccounts")
@CrossOrigin("*")
public class SavingAccountConroller {
    private SavingAccountService savingAccountService;
    @GetMapping("/all")
    public List<SavingAccountRespenseDTO> getAllSavingAccounts(){
        return savingAccountService.getAllSavingAccounts();
    }

    @GetMapping("/{id}")
    public SavingAccountRespenseDTO getSavingAccount(@PathVariable String id) throws  SavingAccountNotFoundException {
        if(id!=null) return savingAccountService.getSavingAccount(id);
        return null;
    }

    @PostMapping("/add")
    public SavingAccountRespenseDTO createSavingAccount(@RequestBody SavingAccountRequestDTO savingAccountRequestDTO) throws CustomerNotFoundException {
        if(savingAccountRequestDTO!=null) return savingAccountService.createSavingAccount( savingAccountRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public SavingAccountRespenseDTO updateSavingAccount(@PathVariable String id, @RequestBody SavingAccountRequestDTO savingAccountRequestDTO) throws SavingAccountNotFoundException, CustomerNotFoundException {
        if(savingAccountRequestDTO!=null && id!=null) return savingAccountService.updateSavingAccount(id,savingAccountRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSavingAccount(@PathVariable String id) throws  SavingAccountNotFoundException {
        if( id!=null) return savingAccountService.deleteSavingAccount(id);
        return false;
    }
}
