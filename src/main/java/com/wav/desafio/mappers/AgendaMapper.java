package com.wav.desafio.mappers;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.model.AgendaDTO;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper
public interface AgendaMapper
{
    AgendaDTO agendaToAgendaDTO( AgendaEntity agendaEntity );
}
