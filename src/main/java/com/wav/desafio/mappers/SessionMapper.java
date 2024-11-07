package com.wav.desafio.mappers;

import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.model.SessionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SessionMapper
{
    SessionDTO sessionToSessionDTO( SessionEntity session );

    SessionEntity sessionDTOToSessionEntity( SessionDTO session );
}
