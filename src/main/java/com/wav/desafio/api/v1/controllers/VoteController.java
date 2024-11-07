package com.wav.desafio.api.v1.controllers;

import com.wav.desafio.api.v1.VoteControllerAPI;
import com.wav.desafio.model.VoteDTO;
import com.wav.desafio.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController implements VoteControllerAPI
{
    private final VoteService voteService;

    @Override
    public ResponseEntity<Object> create( VoteDTO voteDTO )
    {
        try
        {
            return ResponseEntity.status( HttpStatus.CREATED).body( voteService.create( voteDTO ) );
        }

        catch( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }
}
