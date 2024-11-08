package com.wav.desafio.repositories;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.entities.AssociateEntity;
import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.entities.VoteEntity;
import com.wav.desafio.model.dto.VoteResultDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class AgendaRepositoryTest
{
    
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Mock
    private AgendaEntity agendaEntity;
    @Mock
    private AssociateEntity associateEntity;
    @Mock
    private SessionEntity sessionEntity;
    @Mock
    private VoteEntity voteEntity;

    @BeforeEach
    void before() {
        agendaEntity = agendaRepository.save(AgendaEntity.builder()
                                                         .name("Test Agenda")
                                                         .description("Test Description")
                                                         .build());

        associateEntity = associateRepository.save(AssociateEntity.builder()
                                                                  .name("Test Associate")
                                                                  .cpf("661.338.900-53")
                                                                  .build());

        sessionEntity = sessionRepository.save(SessionEntity.builder()
                                                            .agendaId(agendaEntity.getId())
                                                            .duration(60)
                                                            .build());

        voteEntity = voteRepository.save(VoteEntity.builder()
                                                   .associateId(associateEntity.getId())
                                                   .sessionId(sessionEntity.getId())
                                                   .vote(true)
                                                   .build());
        
    }

    @Test
    void shouldFindById() {
        Optional<AgendaEntity> AgendaEntityById = agendaRepository.findById(agendaEntity.getId());

        Assertions.assertNotNull(AgendaEntityById);
    }

    @Test
    void shouldCountVotes() {
        VoteResultDTO voteResultResponseDTO = agendaRepository.countVotes(agendaEntity.getId());

        Assertions.assertEquals(voteResultResponseDTO, VoteResultDTO.builder()
                                                                            .proVotes(1L)
                                                                            .againstVotes(0L)
                                                                            .build());
    }
}
