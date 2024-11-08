package com.wav.desafio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wav.desafio.model.CreatedAgendaDTO;
import com.wav.desafio.model.dto.AgendaDTO;
import com.wav.desafio.model.dto.VoteResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Agenda Controller")
public class AgendaControllerTest
{
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CreatedAgendaDTO createdAgendaDTO;
    @Mock
    private AgendaDTO AgendaResponseDTO;
    @Mock
    private VoteResultDTO voteResultDTO;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper = new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String mockedTitle = "Agenda Test";
        String mockedDescription = "Description test";
        LocalDateTime mocekdCreatedAt = LocalDateTime.of(2024, 04, 28, 07, 00, 00);

        createdAgendaDTO = CreatedAgendaDTO.builder()
                                           .title(mockedTitle)
                                           .description(mockedDescription)
                                           .build();

        AgendaResponseDTO = AgendaDTO.builder()
                                             .id(1)
                                             .name(mockedTitle)
                                             .description(mockedDescription)
                                             .build();

        voteResultDTO = VoteResultDTO.builder()
                                             .name(mockedTitle)
                                             .description(mockedDescription)
                                             .againstVotes(0L)
                                             .result("Aprovada")
                                             .build();
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    @DisplayName("Should Create Agenda")
    void shouldCreateAgenda() throws Exception {
        mockMvc.perform(post("/api/v1/Agenda/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                    .content(objectMapper.writeValueAsString(createdAgendaDTO)))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    @DisplayName("Should Not Create Agenda")
    void shouldNotCreateAgenda() throws Exception {
        mockMvc.perform(post("/api/v1/Agenda/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                    .content(objectMapper.writeValueAsString(CreatedAgendaDTO.builder().build())))
               .andExpect(status().isBadRequest());
    }

    @Test
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Find By Id") 
    void shouldFindById() throws Exception {
        mockMvc.perform(get("/api/v1/Agenda/{AgendaId}", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(objectMapper.writeValueAsString(AgendaResponseDTO)));
    }

    @Test
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Find All")
    void shouldFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/Agenda/", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(objectMapper.writeValueAsString(List.of(AgendaResponseDTO))));
    }

    @Test
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAssociate.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertSession.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertVote.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Count Votes")
    void shouldCountVotes() throws Exception {
        mockMvc.perform(get("/api/v1/Agenda/count/votes/{AgendaId}", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(objectMapper.writeValueAsString(voteResultDTO)));
    }
}
