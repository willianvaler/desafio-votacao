package com.wav.desafio.api.v1;

import com.wav.desafio.model.dto.SessionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/session")
public interface SessionControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( SessionDTO session );

    @GetMapping( "/{sessionId}" )
    public ResponseEntity<Object> getSession( int id );

    @GetMapping( "/" )
    public ResponseEntity<Object> getAllSessions();
}
