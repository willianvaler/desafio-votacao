package com.wav.desafio.services;

import com.wav.desafio.model.SessionDTO;

import java.util.List;

public interface SessionService
{
    public SessionDTO create( SessionDTO session );

    public SessionDTO getSession( int id );

    public List<SessionDTO> getAllSessions();
}
