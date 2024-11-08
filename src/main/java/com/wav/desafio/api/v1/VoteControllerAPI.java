package com.wav.desafio.api.v1;

import com.wav.desafio.model.dto.VoteDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/vote" )
public interface VoteControllerAPI
{
    @PostMapping
    public ResponseEntity<Object> create( @Valid @RequestBody VoteDTO voteDTO );
}
