package com.wav.desafio.repositories;

import com.wav.desafio.entities.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer>
{
}
