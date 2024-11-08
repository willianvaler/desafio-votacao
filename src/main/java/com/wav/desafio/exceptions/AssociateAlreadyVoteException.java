package com.wav.desafio.exceptions;

public class AssociateAlreadyVoteException extends RuntimeException {
    
    public AssociateAlreadyVoteException()
    {
        super("Associado já votou nesta sessão");
    }
}
