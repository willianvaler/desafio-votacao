package com.wav.desafio.exceptions;

public class SessionNotFoundException extends RuntimeException
{
    
    public SessionNotFoundException()
    {
        super("Sessão não encontrada");
    }
}
