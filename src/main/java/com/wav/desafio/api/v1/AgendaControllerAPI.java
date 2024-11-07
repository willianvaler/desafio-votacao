package com.wav.desafio.api.v1;

import com.wav.desafio.model.AgendaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/agenda")
public interface AgendaControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( AgendaDTO agendaDTO );

    @PostMapping( "/{agendaId}" )
    public ResponseEntity<Object> getById( Integer id );

    @PostMapping( "/" )
    public ResponseEntity<Object> getAll();

    @PostMapping( "/count/{agendaId}" )
    public ResponseEntity<Object> countAgendaVotes( Integer id);
}
