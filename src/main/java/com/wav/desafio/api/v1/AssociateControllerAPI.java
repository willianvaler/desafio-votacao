package com.wav.desafio.api.v1;

import com.wav.desafio.model.AssociateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/associate")
public interface AssociateControllerAPI
{
    @PostMapping( "/create" )
    public ResponseEntity<Object> create( AssociateDTO associateDTO );

    @PostMapping( "/{associateId}" )
    public ResponseEntity<Object> getAssociate( Integer id );

    @PostMapping( "/" )
    public ResponseEntity<Object> getAssociates();
}
