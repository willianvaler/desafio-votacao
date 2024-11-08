package com.wav.desafio.services;

import com.wav.desafio.entities.AssociateEntity;
import com.wav.desafio.entities.SessionEntity;
import com.wav.desafio.entities.VoteEntity;
import com.wav.desafio.exceptions.AssociateNotFoundException;
import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.exceptions.SessionClosedException;
import com.wav.desafio.exceptions.SessionNotFoundException;
import com.wav.desafio.model.CreatedVoteDTO;
import com.wav.desafio.model.dto.VoteDTO;
import com.wav.desafio.repositories.AssociateRepository;
import com.wav.desafio.repositories.SessionRepository;
import com.wav.desafio.repositories.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    
    @InjectMocks
    private VoteService voteService;

    @Mock
    private VoteRepository voteRepository;
    @Mock
    private AssociateRepository associateRepository;
    @Mock
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("Should Create Vote")
    void shouldCreateVote() {
        Integer mockedId = 1;

        AssociateEntity associateEntity = AssociateEntity.builder()
                                                         .id(mockedId)
                                                         .name("Associate Test")
                                                         .cpf("661.338.900-53")
                                                         .build();

        VoteEntity voteEntity = VoteEntity.builder()
                                          .associateId(mockedId)
                                          .sessionId(mockedId)
                                          .vote(true)
                                          .build();

        SessionEntity sessionEntity = SessionEntity.builder()
                                                   .duration(60)
                                                   .createdAt(LocalDateTime.now())
                                                   .agendaId(mockedId)
                                                   .build();

        when(associateRepository.findById(mockedId)).thenReturn(Optional.of(associateEntity));
        when(sessionRepository.findById(mockedId)).thenReturn(Optional.of(sessionEntity));
        when(voteRepository.findByAssociateIdAndSessionId(mockedId, mockedId)).thenReturn(Optional.empty());
        when(voteRepository.save(voteEntity)).thenReturn(voteEntity);

        VoteDTO voteResponseDTO = voteService.create( VoteDTO.builder()
                                                               .associate_id(mockedId)
                                                               .session_id(mockedId)
                                                               .vote(true)
                                                               .build());

        Assertions.assertNotNull(voteResponseDTO);
    }

    @Test
    @DisplayName("Should Create Throws FieldValidationException If Missing Fields")
    void shouldCreateThrowsFieldValidationExceptionIfMissingFields() {
        Assertions.assertThrows( FieldValidationException.class, () -> voteService.create(VoteDTO.builder().build()));
    }

    @Test
    @DisplayName("Should Create Throws AssociateNotFoundException If Associate Not Found")
    void shouldCreateThrowsAssociateNotFoundExceptionIfAssociateNotFound() {
        Integer mockedAssociateId = 1;

        when(associateRepository.findById(mockedAssociateId)).thenReturn(Optional.empty());

        Assertions.assertThrows( AssociateNotFoundException.class, () -> voteService.create( VoteDTO.builder()
                                                                                                 .associate_id(mockedAssociateId)
                                                                                                 .session_id(mockedAssociateId)
                                                                                                 .vote(true)
                                                                                                 .build()));
    }

    @Test
    @DisplayName("Should Create Throws SessionNotFoundException If Session Not Found")
    void shouldCreateThrowsSessionNotFoundExceptionIfSessionNotFound() {
        Integer mockedAssociateId = 1;
        Integer mockedSessionId = 1;

        AssociateEntity associateEntity = AssociateEntity.builder()
                                                         .name("Associate Test")
                                                         .cpf("661.338.900-53")
                                                         .build();

        when(associateRepository.findById(mockedAssociateId)).thenReturn(Optional.of(associateEntity));
        when(sessionRepository.findById(mockedSessionId)).thenReturn(Optional.empty());

        Assertions.assertThrows( SessionNotFoundException.class, () -> voteService.create( VoteDTO.builder()
                                                                                                .associate_id(mockedSessionId)
                                                                                                .session_id(mockedSessionId)
                                                                                                .vote(true)
                                                                                                .build()));
    }

    @Test
    @DisplayName("Should Create Throws SessionClosedException If Session is closed")
    void shouldCreateThrowsSessionClosedExceptionIfSessionIsClosed() {
        Integer mockedAssociateId = 1;
        Integer mockedSessionId = 1;
        Integer mockedAgendaId = 1;

        AssociateEntity associateEntity = AssociateEntity.builder()
                                                         .name("Associate Test")
                                                         .cpf("661.338.900-53")
                                                         .build();

        SessionEntity sessionEntity = SessionEntity.builder()
                                                   .duration(0)
                                                   .createdAt(LocalDateTime.now())
                                                   .agendaId(mockedAgendaId)
                                                   .build();

        when(associateRepository.findById(mockedAssociateId)).thenReturn(Optional.of(associateEntity));
        when(sessionRepository.findById(mockedSessionId)).thenReturn(Optional.of(sessionEntity));

        Assertions.assertThrows( SessionClosedException.class, () -> voteService.create( VoteDTO.builder()
                                                                                              .associate_id(mockedSessionId)
                                                                                              .session_id(mockedSessionId)
                                                                                              .vote(true)
                                                                                              .build()));
    }
}
