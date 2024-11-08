package com.wav.desafio.repositories;

import com.wav.desafio.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer>
{
    List<SessionEntity> findAllByAgendaId( Integer id );
}
