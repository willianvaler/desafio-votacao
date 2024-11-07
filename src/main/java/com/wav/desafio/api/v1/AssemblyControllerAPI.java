package com.wav.desafio.api.v1;

import com.wav.desafio.model.AssemblyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/assembly")
public interface AssemblyControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( AssemblyDTO assembly );

    @PostMapping( "/{assemblyId}" )
    public ResponseEntity<Object> getAssembly( int id );

    @PostMapping( "/" )
    public ResponseEntity<Object> getAll();
}
