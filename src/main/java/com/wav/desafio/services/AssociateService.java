package com.wav.desafio.services;

import com.wav.desafio.model.AssociateDTO;

import java.util.List;

public interface AssociateService
{
    public AssociateDTO create( AssociateDTO associateDTO );

    public AssociateDTO getAssociate( Integer id );

    public List<AssociateDTO> getAssociates();
}
