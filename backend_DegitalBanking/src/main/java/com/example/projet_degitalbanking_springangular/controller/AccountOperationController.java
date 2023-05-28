package com.example.projet_degitalbanking_springangular.controller;

import com.example.projet_degitalbanking_springangular.Exception.*;
import com.example.projet_degitalbanking_springangular.dtos.requests.AccountOperationRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.AccountOperationRespenseDTO;
import com.example.projet_degitalbanking_springangular.services.AccountOperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts/operation")
@CrossOrigin("*")
public class AccountOperationController {
    private AccountOperationService accountOperationService;

    @GetMapping("/all/{id}")
    public List<AccountOperationRespenseDTO> getAccountHistory(@PathVariable String id) throws BankAccountNotFoundException {
        return accountOperationService.getAccountHistory(id);
    }

    @GetMapping("/{id}")
    public AccountOperationRespenseDTO getCurrentAccount(@PathVariable Long id) throws AccountOperationNotFoundException {
        if(id!=null) return accountOperationService.getAccountOperation(id);
        return null;
    }

    @PostMapping("/transfert")
    public void createTransfert(@RequestBody AccountOperationRequestDTO accountOperationRequestDTO) throws  BankAccountNotFoundException, BalanceNotSufficientException {
        if(accountOperationRequestDTO!=null)  accountOperationService.Transfet( accountOperationRequestDTO);
    }

    @PostMapping("/debit")
    public void createDebit(@RequestBody AccountOperationRequestDTO accountOperationRequestDTO) throws  BankAccountNotFoundException, BalanceNotSufficientException {
        if(accountOperationRequestDTO!=null)  accountOperationService.Debit( accountOperationRequestDTO);
    }

    @PostMapping("/credit")
    public void createCredit(@RequestBody AccountOperationRequestDTO accountOperationRequestDTO) throws  BankAccountNotFoundException, BalanceNotSufficientException {
        if(accountOperationRequestDTO!=null)  accountOperationService.Credit( accountOperationRequestDTO);
    }

   /* @PutMapping("/{id}")
    public CurrentAccountRespenseDTO updateCurrentAccount(@PathVariable String id, @RequestBody CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException, CurrentAccountNotFoundException {
        if(currentAccountRequestDTO!=null && id!=null) return accountOperationService.updateCurrentAccount(id,currentAccountRequestDTO);
        return null;
    }*/

    @DeleteMapping("/{id}")
    public Boolean deleteAccountOpeation(@PathVariable Long id) throws AccountOperationNotFoundException {
        if( id!=null) return accountOperationService.deleteAccountOperation(id);
        return false;
    }
}
