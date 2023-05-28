package com.example.projet_degitalbanking_springangular.services.servicesImp;

import com.example.projet_degitalbanking_springangular.Exception.BankAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.CustomerNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.mappers.CurrentAccountMapper;
import com.example.projet_degitalbanking_springangular.dtos.mappers.SavingAccountMapper;
import com.example.projet_degitalbanking_springangular.dtos.responses.BankAcountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import com.example.projet_degitalbanking_springangular.entities.CurrentAccount;
import com.example.projet_degitalbanking_springangular.entities.Customer;
import com.example.projet_degitalbanking_springangular.entities.SavingAccount;
import com.example.projet_degitalbanking_springangular.repositories.BankAccountRepository;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import com.example.projet_degitalbanking_springangular.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImp implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private SavingAccountMapper savingAccountMapper;
    private CurrentAccountMapper currentAccountMapper;
    private CustomerRepository customerRepository;

    @Override
    public BankAcountRespenseDTO getAccount(String id) throws BankAccountNotFoundException {
        if(id!=null){

            BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new BankAccountNotFoundException("Bank Account with the id :"+id+"not found!"));
            if(bankAccount!=null){
                if(bankAccount instanceof  SavingAccount) {
                    SavingAccount savingAccount = (SavingAccount)  bankAccount;
                   return savingAccountMapper.fromSavingAccount(savingAccount);
                } else{
                    CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                   return  currentAccountMapper.fromCurrentAccount(currentAccount);
                }
            }

        }
        return null;
    }

    @Override
    public List<BankAcountRespenseDTO> getAccountsCustomer(Long id) throws CustomerNotFoundException {
        if(id!=null){
            Customer customer= customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
            if(customer!=null){
                List<BankAccount> bankAccounts=bankAccountRepository.findByCleEtrangere(id);
                if(bankAccounts!=null){
                    List<BankAcountRespenseDTO> bankAcountRespenseDTOS = bankAccounts.stream()
                            .map(acc -> {
                                        if(acc instanceof  SavingAccount) {
                                            SavingAccount savingAccount = (SavingAccount)  acc;
                                            return savingAccountMapper.fromSavingAccount(savingAccount);
                                        } else{
                                            CurrentAccount currentAccount = (CurrentAccount) acc;
                                            return  currentAccountMapper.fromCurrentAccount(currentAccount);
                                        }
                                    }
                            ).collect(Collectors.toList());

                    return bankAcountRespenseDTOS;
                }

            }
        }
        return null;
    }

    @Override
    public List<BankAcountRespenseDTO> getAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAcountRespenseDTO> bankAcountRespenseDTOS = bankAccounts.stream()
                .map(acc -> {
                            if(acc instanceof  SavingAccount) {
                                SavingAccount savingAccount = (SavingAccount)  acc;
                                return savingAccountMapper.fromSavingAccount(savingAccount);
                            } else{
                                CurrentAccount currentAccount = (CurrentAccount) acc;
                                return  currentAccountMapper.fromCurrentAccount(currentAccount);
                            }
                        }


                        ).collect(Collectors.toList());

        return bankAcountRespenseDTOS;

    }
}
