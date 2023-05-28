package com.example.projet_degitalbanking_springangular.services.servicesImp;

import com.example.projet_degitalbanking_springangular.Exception.CurrentAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.SavingAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.mappers.CurrentAccountMapper;
import com.example.projet_degitalbanking_springangular.dtos.requests.CurrentAccountRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.CurrentAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.CurrentAccount;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.entities.SavingAccount;
import com.example.projet_degitalbanking_springangular.repositories.CurrentAccountRepository;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import com.example.projet_degitalbanking_springangular.services.CurrentAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CurrentAccountServiceImp implements CurrentAccountService {
    private CurrentAccountRepository currentAccountRepository;
    private CurrentAccountMapper currentAccountMapper;

    private CustomerRepository customerRepository;

    @Override
    public CurrentAccountRespenseDTO createCurrentAccount(CurrentAccountRequestDTO currentAccountRequestDTO) throws CustomerNotFoundException {
        if(currentAccountRequestDTO!=null){

           CurrentAccount currentAccount = currentAccountMapper.fromCurrentAccountRequestDTO(currentAccountRequestDTO);

            currentAccountRepository.save(currentAccount);
            return currentAccountMapper.fromCurrentAccount(currentAccount);
        }
        return null;
    }

    @Override
    public CurrentAccountRespenseDTO updateCurrentAccount(String id, CurrentAccountRequestDTO currentAccountRequestDTO) throws CurrentAccountNotFoundException, CustomerNotFoundException {
        if(id!=null && currentAccountRequestDTO!=null){
            CurrentAccount currentAccount = currentAccountRepository.findById(id).orElse(null);
            if(currentAccount!=null){
                if(currentAccountRequestDTO.getStatus()!=null) currentAccount.setStatus(currentAccountRequestDTO.getStatus());
                if(currentAccountRequestDTO.getCurrency()!=null) currentAccount.setCurrency(currentAccountRequestDTO.getCurrency());
                if(currentAccountRequestDTO.getOverDraft()!=null) currentAccount.setOverDraft(currentAccountRequestDTO.getOverDraft());
                if(currentAccountRequestDTO.getBalance()!=null) currentAccount.setBalance(currentAccountRequestDTO.getBalance());
                if(currentAccountRequestDTO.getCustomerId()!=null) {
                    Customer customer = customerRepository.findById(currentAccountRequestDTO.getCustomerId()).orElseThrow( ()-> new CustomerNotFoundException(currentAccountRequestDTO.getCustomerId()));
                    if(customer!=null){
                        currentAccount.setCustomer(customer);
                        // customer.getBankAccounts().add(savingAccount);
                    }
                }
                currentAccountRepository.save(currentAccount);
                return currentAccountMapper.fromCurrentAccount(currentAccount);
            } throw new CurrentAccountNotFoundException(id);
        }
        return null;
    }

    @Override
    public CurrentAccountRespenseDTO getCurrentAccount(String id) throws CurrentAccountNotFoundException {
        if(id!=null){
            CurrentAccount currentAccount = currentAccountRepository.findById(id).orElseThrow(()-> new CurrentAccountNotFoundException(id));
            return currentAccountMapper.fromCurrentAccount(currentAccount);
        }
        return null;
    }

    @Override
    public List<CurrentAccountRespenseDTO> getAllCurrentAccounts() {
        List<CurrentAccount> currentAccount = currentAccountRepository.findAll();
        List<CurrentAccountRespenseDTO> currentAccountsRespenseDTO = currentAccount.stream()
                .map(acc -> currentAccountMapper.fromCurrentAccount(acc))
                .collect(Collectors.toList());

        return currentAccountsRespenseDTO;
    }

    @Override
    public Boolean deleteCurrentAccount(String id) throws CurrentAccountNotFoundException {
        if(id!=null){
            CurrentAccount currentAccount = currentAccountRepository.findById(id).orElseThrow(()-> new CurrentAccountNotFoundException(id));
            if(currentAccount!=null){
                currentAccountRepository.delete(currentAccount);
                return true;
            }

        }
        return false;
    }
}
