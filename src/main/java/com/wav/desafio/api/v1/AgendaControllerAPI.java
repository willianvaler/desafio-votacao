package com.wav.desafio.api.v1;

import com.wav.desafio.model.dto.AgendaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/agenda")
public interface AgendaControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( AgendaDTO agendaDTO );

    @GetMapping( "/{agendaId}" )
    public ResponseEntity<Object> getById( Integer id );

    @GetMapping( "/" )
    public ResponseEntity<Object> getAll();

    @GetMapping( "/count/{agendaId}" )
    public ResponseEntity<Object> countAgendaVotes( Integer id);
}
