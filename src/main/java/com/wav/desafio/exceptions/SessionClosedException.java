package com.wav.desafio.exceptions;

public class SessionClosedException extends RuntimeException {
    
    public SessionClosedException() {
        super("Sessão já encerrada");
    }
}
