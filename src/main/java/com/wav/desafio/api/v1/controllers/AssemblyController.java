package com.wav.desafio.api.v1.controllers;

import com.wav.desafio.api.v1.AssemblyControllerAPI;
import com.wav.desafio.model.AssemblyDTO;
import com.wav.desafio.services.AssemblyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssemblyController implements AssemblyControllerAPI
{
    private final AssemblyService service;

    @Override
    public ResponseEntity<Object> create( AssemblyDTO assembly )
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
    public ResponseEntity<Object> getAssembly( int id )
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
    }
}
