package com.wav.desafio.repositories;

import com.wav.desafio.entities.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer>
{
    Optional<VoteEntity> findByAssociateIdAndSessionId( Integer associateId, Integer sessionId);
}
