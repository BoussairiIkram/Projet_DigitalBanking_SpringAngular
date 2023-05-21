package com.example.projet_degitalbanking_springangular;

import com.example.projet_degitalbanking_springangular.entities.*;
import com.example.projet_degitalbanking_springangular.entities.enums.AccountStatus;
import com.example.projet_degitalbanking_springangular.entities.enums.OperationType;
import com.example.projet_degitalbanking_springangular.repositories.AccountOperationRepository;
import com.example.projet_degitalbanking_springangular.repositories.BankAccountRepository;
import com.example.projet_degitalbanking_springangular.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetDegitalBankingSpringAngularApplication  {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountOperationRepository accountOperationRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetDegitalBankingSpringAngularApplication.class, args);
    }

 /*
    @Override
    public void run(String... args) throws Exception {
       Stream.of("ikram","Rayane","Afaf").forEach(
                name->{
                    Customer customer = Customer.builder()
                            .nom(name)
                            .email(name+"@gmail.com")
                            .dateNaissance(new Date())
                            .build();
                    customerRepository.save(customer);
                });

        customerRepository.findAll().forEach(
                cust->{
                    CurrentAccount currentAccount = new CurrentAccount();
                    currentAccount.setId(UUID.randomUUID().toString());
                    currentAccount.setCreatedAt(new Date());
                    currentAccount.setCurrency("MAD");
                    currentAccount.setStatus(AccountStatus.ACTIVATED);
                    currentAccount.setCustomer(cust);
                    currentAccount.setOverDraft(9000.0);
                    bankAccountRepository.save(currentAccount);

                    SavingAccount savingAccount = new SavingAccount();
                    savingAccount.setId(UUID.randomUUID().toString());
                    savingAccount.setCreatedAt(new Date());
                    savingAccount.setCurrency("MAD");
                    savingAccount.setStatus(AccountStatus.ACTIVATED);
                    savingAccount.setCustomer(cust);
                    savingAccount.setInterestRate(5.5);
                    bankAccountRepository.save(savingAccount);
                });

        bankAccountRepository.findAll().forEach(
                acc->{
                    for (int i=0;i<5;i++){
                        AccountOperation accountOperation = AccountOperation.builder()
                                .dateOperation(new Date())
                                .amount(Math.random()*12000)
                                .opType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREDIT)
                                .bankAccount(acc)
                                .build();
                        accountOperationRepository.save(accountOperation);

                    }

                });


        //if bankAccount instance of SavingAccount

        }*/

}
