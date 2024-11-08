package com.wav.desafio.repositories;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.entities.AssociateEntity;
import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.entities.VoteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class VoteRepositoryTest {
    
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Mock
    private VoteEntity voteEntity;
    @Mock
    private AssociateEntity associateEntity;
    @Mock
    private AgendaEntity agendaEntity;
    @Mock
    private SessionEntity sessionEntity;

    @BeforeEach
    void before() {
        associateEntity = associateRepository.save( AssociateEntity.builder()
                                                                  .name("Test Associate")
                                                                  .cpf("661.338.900-53")
                                                                  .build());

        agendaEntity = agendaRepository.save( AgendaEntity.builder()
                                                         .name("Test agenda")
                                                         .description("Test Description")
                                                         .build());

        sessionEntity = sessionRepository.save( SessionEntity.builder()
                                                            .agendaId(agendaEntity.getId())
                                                            .duration(60)
                                                            .build());

        voteEntity = voteRepository.save( VoteEntity.builder()
                                                   .associateId(associateEntity.getId())
                                                   .sessionId(sessionEntity.getId())
                                                   .vote(true)
                                                   .build());
    }

    @Test
    void shouldFindById() {
        Optional<VoteEntity> voteEntityById = voteRepository.findById(voteEntity.getId());

        Assertions.assertNotNull(voteEntityById);
    }

    @Test
    void shouldFindByAssociateIdAndSessionId() {
        Optional<VoteEntity> voteEntity = voteRepository.findByAssociateIdAndSessionId(associateEntity.getId(), sessionEntity.getId());

        Assertions.assertNotNull(voteEntity);
    }
}
