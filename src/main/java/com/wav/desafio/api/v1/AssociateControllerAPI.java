package com.wav.desafio.api.v1;

import com.wav.desafio.model.dto.AssociateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/associate")
public interface AssociateControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( AssociateDTO associateDTO );

    @GetMapping( "/{associateId}" )
    public ResponseEntity<Object> getAssociate( Integer id );

    @GetMapping( "/" )
    public ResponseEntity<Object> getAssociates();
}
