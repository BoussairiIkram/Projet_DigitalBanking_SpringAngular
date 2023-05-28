package com.example.projet_degitalbanking_springangular.services.servicesImp;

import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.SavingAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.mappers.CustomerMapper;
import com.example.projet_degitalbanking_springangular.dtos.mappers.SavingAccountMapper;
import com.example.projet_degitalbanking_springangular.dtos.requests.SavingAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CustomerRespenseDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.entities.SavingAccount;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import com.example.projet_degitalbanking_springangular.repositories.SavingAccountRepository;
import com.example.projet_degitalbanking_springangular.services.SavingAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SavingAccountServiceImp implements SavingAccountService {
    private SavingAccountRepository savingAccountRepository;
    private SavingAccountMapper savingAccountMapper;

    private CustomerRepository customerRepository;

    @Override
    public SavingAccountRespenseDTO createSavingAccount(SavingAccountRequestDTO savingAccountRequestDTO) throws CustomerNotFoundException {
        if(savingAccountRequestDTO!=null){

            SavingAccount savingAccount = savingAccountMapper.fromSavingAccountRequestDTO(savingAccountRequestDTO);

            savingAccountRepository.save(savingAccount);
            return savingAccountMapper.fromSavingAccount(savingAccount);
        }
        return null;
    }

    @Override
    public SavingAccountRespenseDTO updateSavingAccount(String id, SavingAccountRequestDTO savingAccountRequestDTO) throws SavingAccountNotFoundException, CustomerNotFoundException {
        if(id!=null && savingAccountRequestDTO!=null){
            SavingAccount savingAccount = savingAccountRepository.findById(id).orElse(null);
            if(savingAccount!=null){
                if(savingAccountRequestDTO.getStatus()!=null) savingAccount.setStatus(savingAccountRequestDTO.getStatus());
                if(savingAccountRequestDTO.getCurrency()!=null) savingAccount.setCurrency(savingAccountRequestDTO.getCurrency());
                if(savingAccountRequestDTO.getInterestRate()!=null) savingAccount.setInterestRate(savingAccountRequestDTO.getInterestRate());
                if(savingAccountRequestDTO.getBalance()!=null) savingAccount.setBalance(savingAccountRequestDTO.getBalance());
                if(savingAccountRequestDTO.getCustomerId()!=null) {
                    Customer customer = customerRepository.findById(savingAccountRequestDTO.getCustomerId()).orElseThrow( ()-> new CustomerNotFoundException(savingAccountRequestDTO.getCustomerId()));
                    if(customer!=null){
                        savingAccount.setCustomer(customer);
                       // customer.getBankAccounts().add(savingAccount);
                    }
                }
                savingAccountRepository.save(savingAccount);
                return savingAccountMapper.fromSavingAccount(savingAccount);
            } throw new SavingAccountNotFoundException(id);
        }
        return null;
    }

    @Override
    public SavingAccountRespenseDTO getSavingAccount(String id) throws SavingAccountNotFoundException {
        if(id!=null){
            SavingAccount savingAccount = savingAccountRepository.findById(id).orElseThrow(()-> new SavingAccountNotFoundException(id));
            return savingAccountMapper.fromSavingAccount(savingAccount);
        }
        return null;
    }

    @Override
    public List<SavingAccountRespenseDTO> getAllSavingAccounts() {
        List<SavingAccount> savingAccounts = savingAccountRepository.findAll();
        List<SavingAccountRespenseDTO> savingAccountRespenseDTOS = savingAccounts.stream()
                .map(acc -> savingAccountMapper.fromSavingAccount(acc))
                .collect(Collectors.toList());

        return savingAccountRespenseDTOS;    }

    @Override
    public Boolean deleteSavingAccount(String id) throws SavingAccountNotFoundException {
        if(id!=null){
            SavingAccount savingAccount = savingAccountRepository.findById(id).orElseThrow(()-> new SavingAccountNotFoundException(id));
            if(savingAccount!=null){
                savingAccountRepository.delete(savingAccount);
                return true;
            }

        }
        return false;
    }
}
