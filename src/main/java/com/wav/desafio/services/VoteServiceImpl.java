package com.wav.desafio.services;

import com.wav.desafio.mappers.VoteMapper;
import com.wav.desafio.model.VoteDTO;
import com.wav.desafio.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService
{
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Override
    public VoteDTO create( VoteDTO voteDTO )
    {
        return voteMapper.voteToVoteDTO( voteRepository.save( voteMapper.voteDTOToVoteEntity( voteDTO ) ));
    }
}
