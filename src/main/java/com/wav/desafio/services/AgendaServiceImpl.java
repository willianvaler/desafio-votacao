package com.wav.desafio.services;

import com.wav.desafio.exceptions.NotFoundException;
import com.wav.desafio.mappers.AgendaMapper;
import com.wav.desafio.model.AgendaDTO;
import com.wav.desafio.model.VoteResultDTO;
import com.wav.desafio.repositories.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService
{
    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    @Override
    public AgendaDTO create( AgendaDTO agendaDTO )
    {
        return agendaMapper.agendaToAgendaDTO( agendaRepository.save( agendaMapper.agendaDTOToAgendaEntity( agendaDTO ) ));
    }

    @Override
    public AgendaDTO getById( Integer id )
    {
//        return Optional.ofNullable( agendaMapper.agendaToAgendaDTO( agendaRepository.findById( id ).orElse( null ) ) ).orElseThrow( new NotFoundException( "Agenda not found" ) );
        return Optional.ofNullable( agendaMapper.agendaToAgendaDTO( agendaRepository.findById( id ).orElse( null ) ) ).orElseThrow();
    }

    @Override
    public List<AgendaDTO> getAll()
    {
        return agendaRepository.findAll().stream().map( agendaMapper::agendaToAgendaDTO ).collect( Collectors.toList());
    }

    @Override
    public VoteResultDTO countAgendaVotes( Integer id )
    {
        return null;
    }
}
