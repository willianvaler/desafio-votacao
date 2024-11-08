package com.wav.desafio.mappers;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.model.dto.AgendaDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AgendaMapper
{
    AgendaDTO agendaToAgendaDTO( AgendaEntity agendaEntity );

    AgendaEntity agendaDTOToAgendaEntity( AgendaDTO agendaDTO );
}
