package com.wav.desafio.services;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.exceptions.AgendaNotFoundException;
import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.exceptions.SessionNotFoundException;
import com.wav.desafio.exceptions.SessionOpenException;
import com.wav.desafio.model.dto.SessionDTO;
import com.wav.desafio.repositories.AgendaRepository;
import com.wav.desafio.repositories.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    
    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private AgendaRepository agendaRepository;

    @Test
    @DisplayName("Should Create Session")
    void shouldCreateSession() {
        Integer mockedAgendaId = 1;
        Integer mockDuration = 60;

        SessionEntity sessionEntity = SessionEntity.builder()
                                                   .duration(mockDuration)
                                                   .agendaId(mockedAgendaId)
                                                   .build();

        AgendaEntity agendaEntity = AgendaEntity.builder()
                                                .id(mockedAgendaId)
                                                .name("Agenda Test")
                                                .description("Description test")
                                                .build();

        when(agendaRepository.findById(mockedAgendaId)).thenReturn(Optional.of(agendaEntity));
        when(sessionRepository.findAllByAgendaId(mockedAgendaId)).thenReturn(List.of());
        when(sessionRepository.save(sessionEntity)).thenReturn(sessionEntity);

        SessionDTO sessionDTO = sessionService.create( SessionDTO.builder()
                                                                   .duration(mockDuration)
                                                                   .agenda_id(mockedAgendaId)
                                                                   .build());

        Assertions.assertNotNull(sessionDTO);
        Assertions.assertEquals(mockDuration, sessionDTO.getDuration());
        Assertions.assertEquals(mockedAgendaId, sessionDTO.getAgenda_id());
    }

    @Test
    @DisplayName("Should Create Throws FieldValidationException If Missing Fields")
    void shouldCreateThrowsFieldValidationExceptionIfMissingFields() {
        Assertions.assertThrows( FieldValidationException.class, () -> sessionService.create( SessionDTO.builder().build() ) );
    }

    @Test
    @DisplayName("Should Create Throws AgendaNotFoundException If Agenda Not Found")
    void shouldCreateThrowsAgendaNotFoundExceptionIfAgendaNotFound() {
        Integer mockedAgendaId = 1;

        when(agendaRepository.findById(mockedAgendaId)).thenReturn(Optional.empty());

        Assertions.assertThrows( AgendaNotFoundException.class, () -> sessionService.create( SessionDTO.builder()
                                                                                                         .duration(60)
                                                                                                         .agenda_id(mockedAgendaId)
                                                                                                         .build()));
    }

    @Test
    @DisplayName("Should Create Throws AgendaNotFoundException If Agenda Not Found")
    void shouldCreateThrowsSessionOpenExceptionIfAlreadyHaveSessionOpen() {
        Integer mockedAgendaId = 1;

        AgendaEntity agendaEntity = AgendaEntity.builder()
                                                .id(mockedAgendaId)
                                                .name("Agenda Test")
                                                .description("Description test")
                                                .build();

        when(agendaRepository.findById(mockedAgendaId)).thenReturn(Optional.of(agendaEntity));
        when(sessionRepository.findAllByAgendaId(mockedAgendaId)).thenReturn(List.of(SessionEntity.builder()
                                                                                                  .createdAt(LocalDateTime.now())  
                                                                                                  .duration(60)
                                                                                                  .build()));

        Assertions.assertThrows( SessionOpenException.class, () -> sessionService.create( SessionDTO.builder()
                                                                                                     .agenda_id(mockedAgendaId)
                                                                                                     .duration(60)
                                                                                                     .build()));
    }

    @Test
    @DisplayName("Should Find By Id")
    void shouldFindById() {
        Integer mockedId = 1;
        Integer mockedDuration = 60;

        SessionEntity sessionEntity = SessionEntity.builder()
                                                   .id(mockedId)
                                                   .duration(mockedDuration)
                                                   .agendaId(mockedId)
                                                   .build();

        when(sessionRepository.findById(mockedId)).thenReturn(Optional.of(sessionEntity));

        SessionDTO sessionDTO = sessionService.getById(sessionEntity.getId());

        Assertions.assertNotNull(sessionDTO);
        Assertions.assertEquals(mockedId, sessionDTO.getAgenda_id());
        Assertions.assertEquals(mockedDuration, sessionDTO.getDuration());
    }

    @Test
    @DisplayName("Should Find By Id Throws SessionNotFoundException If Agenda Not Found")
    void shouldFindByIdThrowsSessionNotFoundExceptionIfAgendaNotFound() {
        Integer mockedId = 1;

        when(sessionRepository.findById(mockedId)).thenReturn(Optional.empty());

        Assertions.assertThrows( SessionNotFoundException.class, () -> sessionService.getById(mockedId));
    }
}
