package com.wav.desafio.exceptions;

public class WrongCPFException extends RuntimeException
{
    public WrongCPFException() {
        super("CPF não é valido");
    }
}
