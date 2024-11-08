package com.wav.desafio.services;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.exceptions.AgendaNotFoundException;
import com.wav.desafio.mappers.AgendaMapper;
import com.wav.desafio.model.dto.AgendaDTO;
import com.wav.desafio.model.dto.VoteResultDTO;
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
        if ( agendaDTO.getName() == null || agendaDTO.getName().isEmpty() )
        {
            throw new RuntimeException( "O campo título é obrigatório." );
        }

        return agendaMapper.agendaToAgendaDTO( agendaRepository.save( agendaMapper.agendaDTOToAgendaEntity( agendaDTO ) ));
    }

    @Override
    public AgendaDTO getById( Integer id )
    {
        return Optional.ofNullable( agendaMapper.agendaToAgendaDTO( agendaRepository.findById( id )
                                                .orElse( null ) ) )
                                    .orElseThrow( AgendaNotFoundException::new );
    }

    @Override
    public List<AgendaDTO> getAll()
    {
        return agendaRepository.findAll().stream().map( agendaMapper::agendaToAgendaDTO ).collect( Collectors.toList());
    }

    @Override
    public VoteResultDTO countVotes( Integer id )
    {
        AgendaEntity agenda = agendaRepository.findById( id ).orElseThrow( () -> new RuntimeException( "A pauta em questão não foi encontrada." ) );

        VoteResultDTO result = agendaRepository.countVotes( id );

        return VoteResultDTO.builder()
                                .name( agenda.getName() )
                                .description( agenda.getDescription() )
                                .proVotes( result.getProVotes() )
                                .againstVotes( result.getAgainstVotes() )
                                .result( result.getResult() ).build();
    }
}
