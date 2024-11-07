package com.wav.desafio.api.v1.controllers;

import com.wav.desafio.api.v1.AgendaControllerAPI;
import com.wav.desafio.model.AgendaDTO;
import com.wav.desafio.services.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgendaController implements AgendaControllerAPI
{

    private final AgendaService service;

    @Override
    public ResponseEntity<Object> create( AgendaDTO agendaDTO )
    {
        try
        {
            return ResponseEntity.status( HttpStatus.CREATED).body( service.create( session ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getById( Integer id )
    {
        try
        {
            return ResponseEntity.ok().body( service.getById( id ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getAll()
    {
        try
        {
            return ResponseEntity.ok().body( service.getAll() );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> countAgendaVotes( Integer id )
    {
        try
        {
            return ResponseEntity.ok().body( service.countAgendaVotes( id ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }
}
