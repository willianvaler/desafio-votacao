package com.wav.desafio.mappers;

import com.wav.desafio.entities.VoteEntity;
import com.wav.desafio.model.dto.VoteDTO;
import org.mapstruct.Mapper;

@Mapper
public interface VoteMapper
{
    VoteDTO voteToVoteDTO( VoteEntity voteEntity );

    VoteEntity voteDTOToVoteEntity( VoteDTO voteDTO );
}
