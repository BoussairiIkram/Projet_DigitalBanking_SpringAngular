package com.example.projet_degitalbanking_springangular.Exception;

public class SavingAccountNotFoundException extends Exception{
    public SavingAccountNotFoundException(String id) {
        super("Savin Account with id : " + id + " not found");
    }

}
