package com.example.projet_degitalbanking_springangular.controller;

import com.example.projet_degitalbanking_springangular.Exception.CurrentAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.requests.CurrentAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CurrentAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.services.CurrentAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts/currentAccounts")
@CrossOrigin("*")
public class CurrentAccountConroller {
    private CurrentAccountService currentAccountService;

    @GetMapping("/all")
    public List<CurrentAccountRespenseDTO> getAllCurrentAccounts(){
        return currentAccountService.getAllCurrentAccounts();
    }

    @GetMapping("/{id}")
    public CurrentAccountRespenseDTO getCurrentAccount(@PathVariable String id) throws CurrentAccountNotFoundException {
        if(id!=null) return currentAccountService.getCurrentAccount(id);
        return null;
    }

    @PostMapping("/add")
    public CurrentAccountRespenseDTO createCurrentAccount(@RequestBody CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException {
        if(currentAccountRequestDTO!=null) return currentAccountService.createCurrentAccount( currentAccountRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public CurrentAccountRespenseDTO updateCurrentAccount(@PathVariable String id, @RequestBody CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException, CurrentAccountNotFoundException {
        if(currentAccountRequestDTO!=null && id!=null) return currentAccountService.updateCurrentAccount(id,currentAccountRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCurrentAccount(@PathVariable String id) throws CurrentAccountNotFoundException {
        if( id!=null) return currentAccountService.deleteCurrentAccount(id);
        return false;
    }
}
