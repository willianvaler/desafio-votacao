package com.wav.desafio.repositories;

import com.wav.desafio.entities.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociateRepository extends JpaRepository<AssociateEntity, Integer>
{
    Optional<AssociateEntity> findByCpf( String cpf);
}
