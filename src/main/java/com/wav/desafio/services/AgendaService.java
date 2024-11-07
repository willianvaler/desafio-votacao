package com.wav.desafio.services;

import com.wav.desafio.model.AgendaDTO;
import com.wav.desafio.model.VoteResultDTO;

import java.util.List;

public interface AgendaService
{
    public AgendaDTO create( AgendaDTO agendaDTO );
    public AgendaDTO getById( Integer id );
    public List<AgendaDTO> getAll();
    public VoteResultDTO countVotes( Integer id);
}
