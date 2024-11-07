package com.wav.desafio.services;

import com.wav.desafio.mappers.SessionMapper;
import com.wav.desafio.model.SessionDTO;
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
        return sessionMapper.sessionToSessionDTO( sessionRepository.save( sessionMapper.sessionDTOToSessionEntity( session ) ));
    }

    @Override
    public SessionDTO getById( int id )
    {
        return Optional.ofNullable( sessionMapper.sessionToSessionDTO( sessionRepository.findById( id ).orElse( null ) ) ).orElseThrow();
    }

    @Override
    public List<SessionDTO> getAll()
    {
        return sessionRepository.findAll().stream().map( sessionMapper::sessionToSessionDTO ).collect( Collectors.toList());
    }
}
