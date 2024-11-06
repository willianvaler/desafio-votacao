package com.wav.desafio.repositories;

import com.wav.desafio.entities.AssemblyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssemblyRepository extends JpaRepository<AssemblyEntity, Integer>
{
}
