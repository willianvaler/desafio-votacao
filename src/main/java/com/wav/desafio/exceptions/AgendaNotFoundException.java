package com.wav.desafio.exceptions;

public class AgendaNotFoundException extends RuntimeException {
    
    public AgendaNotFoundException() {
        super("Pauta não encontrada");
    }
}
