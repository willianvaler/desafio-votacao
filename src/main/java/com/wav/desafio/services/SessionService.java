package com.wav.desafio.services;

import com.wav.desafio.model.dto.SessionDTO;

import java.util.List;

public interface SessionService
{
    public SessionDTO create( SessionDTO session );

    public SessionDTO getById( int id );

    public List<SessionDTO> getAll();
}
