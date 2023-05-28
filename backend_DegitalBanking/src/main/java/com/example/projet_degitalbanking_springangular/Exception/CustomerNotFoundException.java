package com.example.projet_degitalbanking_springangular.Exception;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(Long id) {
        super("Customer with id : " + id + " not found");
    }

}
