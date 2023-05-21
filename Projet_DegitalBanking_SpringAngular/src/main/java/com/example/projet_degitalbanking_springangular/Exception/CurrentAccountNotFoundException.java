package com.example.projet_degitalbanking_springangular.Exception;

public class CurrentAccountNotFoundException extends Exception{
    public CurrentAccountNotFoundException(String id) {
        super("Current Account with id : " + id + " not found");
    }

}
