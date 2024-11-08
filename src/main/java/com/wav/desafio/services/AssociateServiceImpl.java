package com.wav.desafio.services;

import com.wav.desafio.exceptions.AssociateFoundException;
import com.wav.desafio.exceptions.AssociateNotFoundException;
import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.exceptions.WrongCPFException;
import com.wav.desafio.mappers.AssociateMapper;
import com.wav.desafio.model.dto.AssociateDTO;
import com.wav.desafio.repositories.AssociateRepository;
import com.wav.desafio.utils.CPFUtilities;
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
        if ( associateDTO.getCpf() == null || associateDTO.getCpf().isEmpty() )
        {
            throw new FieldValidationException("cpf");
        }

        if ( associateDTO.getName() == null || associateDTO.getName().isEmpty() )
        {
            throw new FieldValidationException("name");
        }

        this.associateRepository.findByCpf( associateDTO.getCpf()).ifPresent( user -> {
            throw new AssociateFoundException();
        });

        if ( ! CPFUtilities.validateCPF( associateDTO.getCpf()) ) {
            throw new WrongCPFException();
        }

        return associateMapper.associateToAssociateDTO( associateRepository.save( associateMapper.associateDTOToAssociateEntity( associateDTO ) ) );
    }

    @Override
    public AssociateDTO getById( Integer id )
    {
        return Optional.ofNullable( associateMapper.associateToAssociateDTO( associateRepository.findById( id ).orElseThrow( AssociateNotFoundException::new ) ) ).orElseThrow();
    }

    @Override
    public List<AssociateDTO> getAll()
    {
        return associateRepository.findAll().stream().map( associateMapper::associateToAssociateDTO ).collect( Collectors.toList());
    }
}
