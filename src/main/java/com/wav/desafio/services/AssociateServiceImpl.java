package com.wav.desafio.services;

import com.wav.desafio.mappers.AssociateMapper;
import com.wav.desafio.model.AssociateDTO;
import com.wav.desafio.repositories.AssociateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class AssociateServiceImpl implements AssociateService
{
    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;

    @Override
    public AssociateDTO create( AssociateDTO associateDTO )
    {
        return associateMapper.associateToAssociateDTO( associateRepository.save( associateMapper.associateDTOToAssociateEntity( associateDTO ) ) );
    }

    @Override
    public AssociateDTO getById( Integer id )
    {
        return Optional.ofNullable( associateMapper.associateToAssociateDTO( associateRepository.findById( id ).orElse( null ) ) ).orElseThrow();
    }

    @Override
    public List<AssociateDTO> getAll()
    {
        return associateRepository.findAll().stream().map( associateMapper::associateToAssociateDTO ).collect( Collectors.toList());
    }
}
