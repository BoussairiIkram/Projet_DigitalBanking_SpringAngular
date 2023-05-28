package com.example.projet_degitalbanking_springangular.Exception;

public class BalanceNotSufficientException extends Exception{
    public BalanceNotSufficientException(String id) {
        super("Bank Account with id : " + id + " not found");
    }

}
