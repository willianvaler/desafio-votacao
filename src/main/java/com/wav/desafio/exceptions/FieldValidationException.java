package com.wav.desafio.exceptions;

public class FieldValidationException extends RuntimeException
{
    public FieldValidationException ( String message )
    {
        super( "O campo '" + message + "' é obrigatório" );
    }
}
