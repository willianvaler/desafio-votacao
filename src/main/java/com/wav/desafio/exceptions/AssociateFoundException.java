package com.wav.desafio.exceptions;

public class AssociateFoundException extends RuntimeException {
    public AssociateFoundException() {
        super("Associado já cadastrado");
    }
}
