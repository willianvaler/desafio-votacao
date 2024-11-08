package com.wav.desafio.api.v1.controllers;

import com.wav.desafio.api.v1.AssociateControllerAPI;
import com.wav.desafio.model.dto.AssociateDTO;
import com.wav.desafio.services.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssociateController implements AssociateControllerAPI
{
    private final AssociateService associateService;

    @Override
    public ResponseEntity<Object> create( AssociateDTO associateDTO )
    {
        try
        {
            return ResponseEntity.status( HttpStatus.CREATED).body( associateService.create( associateDTO ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getAssociate( Integer id )
    {
        try
        {
            return ResponseEntity.ok().body( associateService.getById( id ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getAssociates()
    {
        try
        {
            return ResponseEntity.ok().body( associateService.getAll() );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }
}
