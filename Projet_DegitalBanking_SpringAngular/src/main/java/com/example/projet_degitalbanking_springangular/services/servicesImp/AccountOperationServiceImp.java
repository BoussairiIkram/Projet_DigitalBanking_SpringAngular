package com.example.projet_degitalbanking_springangular.services.servicesImp;

import com.example.projet_degitalbanking_springangular.Exception.AccountOperationNotFoundException;
import com.example.projet_degitalbanking_springangular.Exception.BalanceNotSufficientException;
import com.example.projet_degitalbanking_springangular.Exception.BankAccountNotFoundException;
import com.example.projet_degitalbanking_springangular.dtos.mappers.AccountOperationMapper;
import com.example.projet_degitalbanking_springangular.dtos.requests.AccountOperationRequestDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.AccountOperationRespenseDTO;
import com.example.projet_degitalbanking_springangular.dtos.responses.SavingAccountRespenseDTO;
import com.example.projet_degitalbanking_springangular.entities.AccountOperation;
import com.example.projet_degitalbanking_springangular.entities.BankAccount;
import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import com.example.projet_degitalbanking_springangular.repositories.AccountOperationRepository;
import com.example.projet_degitalbanking_springangular.repositories.BankAccountRepository;
import com.example.projet_degitalbanking_springangular.services.AccountOperationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountOperationServiceImp implements AccountOperationService {

    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private AccountOperationMapper accountOperationMapper;
    @Override
    public void Debit(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException {
        if(accountOperationRequestDTO!=null && accountOperationRequestDTO.getIdBankAccountSource()!=null){
            BankAccount bankAccount = bankAccountRepository.findById(accountOperationRequestDTO.getIdBankAccountSource()).orElseThrow(()-> new BalanceNotSufficientException(accountOperationRequestDTO.getIdBankAccountSource()));
            if(bankAccount!=null ){
                if(bankAccount.getBalance()>accountOperationRequestDTO.getAmount()){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setDateOperation(new Date());
                    accountOperation.setDescription(accountOperationRequestDTO.getDescription());
                    accountOperation.setOpType(OperationType.DEBIT);
                    accountOperation.setAmount(accountOperationRequestDTO.getAmount());
                    accountOperation.setBankAccount(bankAccount);
                    bankAccount.setBalance(bankAccount.getBalance()-accountOperationRequestDTO.getAmount());
                    bankAccountRepository.save(bankAccount);
                    accountOperationRepository.save(accountOperation);
                } else throw new BalanceNotSufficientException("Balance note sufficient for Bank Account with id"+accountOperationRequestDTO.getIdBankAccountSource());
            } else throw new BankAccountNotFoundException(accountOperationRequestDTO.getIdBankAccountSource());

    }

    }

    @Override
    public void Credit(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException {
        if(accountOperationRequestDTO!=null && accountOperationRequestDTO.getIdBankAccountDestination()!=null){
            BankAccount bankAccount = bankAccountRepository.findById(accountOperationRequestDTO.getIdBankAccountDestination()).orElseThrow(()-> new BalanceNotSufficientException(accountOperationRequestDTO.getIdBankAccountDestination()));
            if(bankAccount!=null ){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setDateOperation(new Date());
                    accountOperation.setDescription(accountOperationRequestDTO.getDescription());
                    accountOperation.setOpType(OperationType.CREDIT);
                    accountOperation.setAmount(accountOperationRequestDTO.getAmount());
                    accountOperation.setBankAccount(bankAccount);
                    bankAccount.setBalance(bankAccount.getBalance()+accountOperationRequestDTO.getAmount());
                    bankAccountRepository.save(bankAccount);
                    accountOperationRepository.save(accountOperation);
            } else throw new BankAccountNotFoundException(accountOperationRequestDTO.getIdBankAccountDestination());
        }
    }

    @Override
    public void Transfet(AccountOperationRequestDTO accountOperationRequestDTO) throws BalanceNotSufficientException, BankAccountNotFoundException {
            if(accountOperationRequestDTO!=null){
                Debit(accountOperationRequestDTO);
                Credit(accountOperationRequestDTO);
            }
    }

    @Override
    public List<AccountOperationRespenseDTO> getAccountHistory(String id) throws BankAccountNotFoundException {
        if(id!=null){
            BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()->new BankAccountNotFoundException(id));
            if(bankAccount!=null){
                List<AccountOperation> operations = accountOperationRepository.findByCleEtrangere(id);
                if(operations!=null){
                    List<AccountOperationRespenseDTO> respenseDTOS = operations.stream()
                            .map(acc -> accountOperationMapper.fromAccountOperation(acc))
                            .collect(Collectors.toList());

                    return respenseDTOS;
                }
            }
        }
        return null;
    }

    @Override
    public AccountOperationRespenseDTO getAccountOperation(Long id) throws AccountOperationNotFoundException {
        AccountOperation accountOperation = accountOperationRepository.findById(id).orElseThrow(()-> new AccountOperationNotFoundException("Account Operation with id :"+id+"not found"));
        if(accountOperation!=null){
            return accountOperationMapper.fromAccountOperation(accountOperation);
        }
        return null;
    }

    @Override
    public Boolean deleteAccountOperation(Long id) throws AccountOperationNotFoundException {
        AccountOperation accountOperation = accountOperationRepository.findById(id).orElseThrow(()-> new AccountOperationNotFoundException("Account Operation with id :"+id+"not found"));
        if(accountOperation!=null) {
            accountOperationRepository.delete(accountOperation);
            return true;
        }
        return false;
    }
}
