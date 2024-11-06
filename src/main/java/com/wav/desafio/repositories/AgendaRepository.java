package com.wav.desafio.repositories;

import com.wav.desafio.entities.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Integer>
{
}
