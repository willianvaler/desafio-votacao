package com.wav.desafio.services;

import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.exceptions.AssociateAlreadyVoteException;
import com.wav.desafio.exceptions.AssociateNotFoundException;
import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.exceptions.SessionClosedException;
import com.wav.desafio.exceptions.SessionNotFoundException;
import com.wav.desafio.mappers.VoteMapper;
import com.wav.desafio.model.dto.VoteDTO;
import com.wav.desafio.repositories.AssociateRepository;
import com.wav.desafio.repositories.SessionRepository;
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
    private final AssociateRepository associateRepository;
    private final SessionRepository sessionRepository;

    private final VoteMapper voteMapper;

    @Override
    public VoteDTO create( VoteDTO voteDTO )
    {
        if ( voteDTO.getAssociate_id() == null || voteDTO.getAssociate_id() == 0 )
        {
            throw new FieldValidationException("associateId");
        }

        if ( voteDTO.getSession_id() == null || voteDTO.getSession_id() == 0 )
        {
            throw new FieldValidationException("sessionId");
        }

        this.associateRepository.findById( voteDTO.getAssociate_id()).orElseThrow( AssociateNotFoundException::new );

        SessionEntity sessionEntity = this.sessionRepository.findById( voteDTO.getSession_id()).orElseThrow( SessionNotFoundException::new );

        if(sessionEntity.isClosed()) {
            throw new SessionClosedException();
        }

        this.voteRepository.findByAssociateIdAndSessionId(voteDTO.getAssociate_id(), voteDTO.getSession_id()).ifPresent( vote ->
        {
            throw new AssociateAlreadyVoteException();
        });

        return voteMapper.voteToVoteDTO( voteRepository.save( voteMapper.voteDTOToVoteEntity( voteDTO ) ));
    }
}
