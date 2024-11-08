package com.wav.desafio.services;

import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.exceptions.SessionNotFoundException;
import com.wav.desafio.exceptions.SessionOpenException;
import com.wav.desafio.mappers.SessionMapper;
import com.wav.desafio.model.dto.SessionDTO;
import com.wav.desafio.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService
{
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionDTO create( SessionDTO session )
    {
        if ( session.getAgenda_id() == null || session.getAgenda_id() == 0 )
        {
            throw new FieldValidationException("agenda_id");
        }

        sessionRepository.findAllByAgendaId( session.getAgenda_id() ).forEach( s ->
        {
            if( !s.isClosed() )
            {
                throw new SessionOpenException();
            }
        } );

        return sessionMapper.sessionToSessionDTO( sessionRepository.save( sessionMapper.sessionDTOToSessionEntity( session ) ));
    }

    @Override
    public SessionDTO getById( int id )
    {
        return Optional.ofNullable( sessionMapper.sessionToSessionDTO( sessionRepository.findById( id ).orElseThrow( SessionNotFoundException::new ) ) ).orElseThrow();
    }

    @Override
    public List<SessionDTO> getAll()
    {
        return sessionRepository.findAll().stream().map( sessionMapper::sessionToSessionDTO ).collect( Collectors.toList());
    }
}
