package com.wav.desafio.mappers;

import com.wav.desafio.entities.AssociateEntity;
import com.wav.desafio.model.dto.AssociateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AssociateMapper
{
    AssociateDTO associateToAssociateDTO( AssociateEntity associateEntity );

    AssociateEntity associateDTOToAssociateEntity( AssociateDTO associateDTO );
}
