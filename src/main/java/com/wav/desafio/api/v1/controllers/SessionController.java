package com.wav.desafio.api.v1.controllers;

import com.wav.desafio.api.v1.SessionControllerAPI;
import com.wav.desafio.model.SessionDTO;
import com.wav.desafio.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionController implements SessionControllerAPI
{
    private final SessionService sessionService;

    @Override
    public ResponseEntity<Object> create( SessionDTO session )
    {
        try
        {
            return ResponseEntity.status( HttpStatus.CREATED).body( sessionService.create( session ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getSession( int id )
    {
        try
        {
            return ResponseEntity.ok().body( sessionService.getSession( id ) );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @Override
    public ResponseEntity<Object> getAllSessions()
    {
        try
        {
            return ResponseEntity.ok().body( sessionService.getAllSessions() );
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }
}
