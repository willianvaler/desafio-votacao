package com.wav.desafio.repositories;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.model.VoteResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Integer>
{
    @Query(
            """
            SELECT NEW com.wav.desafio.model.VoteResultDTO
            (
               SUM(CASE WHEN V.vote = TRUE THEN 1 ELSE 0 END),
               SUM(CASE WHEN V.vote = FALSE THEN 1 ELSE 0 END)
            )
            FROM AgendaEntity A
                JOIN SessionEntity S ON S.agenda_id = A.id
                JOIN VoteEntity V ON V.session_id = S.id
            WHERE
                S.createdAt = (SELECT MAX(S2.createdAt) FROM SessionEntity S2 WHERE S2.agenda_id = A.id) 
            AND 
                A.id = :rulingId
        """
    )
    VoteResultDTO countVotes( Integer agendaId );
}
