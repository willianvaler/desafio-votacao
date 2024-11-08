package com.wav.desafio.exceptions;

public class SessionOpenException extends RuntimeException {
    
    public SessionOpenException() {
        super("Já existe uma sessão aberta para esta pauta");
    }
}
