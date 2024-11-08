package com.wav.desafio.services;

import com.wav.desafio.entities.AgendaEntity;
import com.wav.desafio.exceptions.AgendaNotFoundException;
import com.wav.desafio.exceptions.FieldValidationException;
import com.wav.desafio.model.dto.AgendaDTO;
import com.wav.desafio.model.dto.VoteResultDTO;
import com.wav.desafio.repositories.AgendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {

    @InjectMocks
    private AgendaService agendaService;

    @Mock
    private AgendaRepository agendaRepository;

    @Test
    @DisplayName("Should Create Agenda")
    void shouldCreateAgenda() {
        String mockTitle = "Agenda test";
        String mockDescription = "Description test";

        AgendaEntity agendaEntity = AgendaEntity.builder()
                .name(mockTitle)
                .description(mockDescription)
                .build();

        when(agendaRepository.save(agendaEntity)).thenReturn(agendaEntity);

        AgendaDTO agendaDTO = agendaService.create( AgendaDTO.builder()
                                                                .name(mockTitle)
                                                                .description(mockDescription)
                                                                .build());

        Assertions.assertNotNull(agendaDTO);
        Assertions.assertEquals(mockTitle, agendaDTO.getName());
        Assertions.assertEquals(mockDescription, agendaDTO.getDescription());
    }

    @Test
    @DisplayName("Should Create Throws FieldValidationException If Missing Fields")
    void shouldCreateThrowsFieldValidationExceptionIfMissingFields() {
        Assertions.assertThrows( FieldValidationException.class, () -> agendaService.create(AgendaDTO.builder().build()));
    }

    @Test
    @DisplayName("Should Find By Id")
    void shouldFindById() {
        Integer mockedId = 1;
        String mockTitle = "Agenda test";
        String mockDescription = "Description test";

        AgendaEntity agendaEntity = AgendaEntity.builder()
                .id(mockedId)
                .name(mockTitle)
                .description(mockDescription)
                .build();

        when(agendaRepository.findById(mockedId)).thenReturn(Optional.of(agendaEntity));

        AgendaDTO agendaDTO = agendaService.getById(agendaEntity.getId());

        Assertions.assertNotNull(agendaDTO);
        Assertions.assertEquals(mockTitle, agendaDTO.getName());
        Assertions.assertEquals(mockDescription, agendaDTO.getDescription());
    }

    @Test
    @DisplayName("Should Find By Id Throws AgendaNotFoundException If Agenda Not Found")
    void shouldFindByIdThrowsAgendaNotFoundExceptionIfAgendaNotFound() {
        Integer mockedId = 1;

        when(agendaRepository.findById(mockedId)).thenReturn(Optional.empty());

        Assertions.assertThrows( AgendaNotFoundException.class, () -> agendaService.getById(mockedId));
    }

    @Test
    @DisplayName("Should Count Votes")
    void shouldCountVotes() {
        Integer mockedId = 1;
        String mockTitle = "Agenda test";
        String mockDescription = "Description test";
        String mockResult = "Aprovada";

        AgendaEntity agendaEntity = AgendaEntity.builder()
                                                .id(mockedId)
                                                .name(mockTitle)
                                                .description(mockDescription)
                                                .build();

        when(agendaRepository.findById(mockedId)).thenReturn(Optional.of(agendaEntity));
        when(agendaRepository.countVotes(mockedId)).thenReturn( VoteResultDTO.builder()
                                                                                .name(mockTitle)
                                                                                .description(mockDescription)
                                                                                .proVotes(1L)
                                                                                .againstVotes(0L)
                                                                                .result(mockResult)
                                                                                .build());

        VoteResultDTO voteResultDTO = agendaService.countVotes(agendaEntity.getId());

        Assertions.assertNotNull(voteResultDTO);
        Assertions.assertEquals(1, voteResultDTO.getProVotes());
        Assertions.assertEquals(0, voteResultDTO.getAgainstVotes());
        Assertions.assertEquals(mockResult, voteResultDTO.getResult());
        Assertions.assertEquals(mockTitle, voteResultDTO.getName());
        Assertions.assertEquals(mockDescription, voteResultDTO.getDescription());
    }

    @Test
    @DisplayName("Should Count Votes Throw AgendaNotFoundException If Agenda Not Found")
    void shouldCountVotesThrowAgendaNotFoundExceptionIfAgendaNotFound(){
        Integer mockedId = 1;

        when(agendaRepository.findById(mockedId)).thenReturn(Optional.empty());

        Assertions.assertThrows(AgendaNotFoundException.class, () -> agendaService.countVotes(mockedId));
    }
}
