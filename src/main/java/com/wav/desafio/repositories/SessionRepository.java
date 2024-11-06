package com.wav.desafio.repositories;

import com.wav.desafio.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer>
{
}
