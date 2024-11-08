package com.wav.desafio.services;

import com.wav.desafio.model.dto.AgendaDTO;
import com.wav.desafio.model.dto.VoteResultDTO;

import java.util.List;

public interface AgendaService
{
    public AgendaDTO create( AgendaDTO agendaDTO );
    public AgendaDTO getById( Integer id );
    public List<AgendaDTO> getAll();
    public VoteResultDTO countVotes( Integer id);
}
