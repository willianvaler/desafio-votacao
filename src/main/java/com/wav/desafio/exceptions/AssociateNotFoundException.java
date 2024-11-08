package com.wav.desafio.exceptions;

public class AssociateNotFoundException extends RuntimeException
{
    public AssociateNotFoundException() {
        super("Associado não encontrado");
    }
}
