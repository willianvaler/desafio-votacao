package com.wav.desafio.repositories;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.entities.SessionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class SessionRepositoryTest {
    
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AgendaRepository agendaRepository;

    @Mock
    private SessionEntity sessionEntity;
    @Mock
    private AgendaEntity agendaEntity;

    @BeforeEach
    void before()
    {
        agendaEntity = agendaRepository.save( AgendaEntity.builder()
                                                         .name("Test Agenda")
                                                         .description("Test Description")
                                                         .build());

        sessionEntity = sessionRepository.save( SessionEntity.builder()
                                                            .agendaId(agendaEntity.getId())
                                                            .duration(60)
                                                            .build());
    }

    @Test
    void shouldFindById()
    {
        Optional<SessionEntity> sessionEntityById = sessionRepository.findById(sessionEntity.getId());

        Assertions.assertNotNull(sessionEntityById);
    }

    @Test
    void shoudFindAllByAgendaId()
    {
        List<SessionEntity> allSessionsByAgendaId = sessionRepository.findAllByAgendaId( agendaEntity.getId() );

        Assertions.assertEquals(allSessionsByAgendaId.size(), 1);
    }
}
