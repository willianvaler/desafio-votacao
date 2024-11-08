package com.wav.desafio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wav.desafio.model.CreatedSessionDTO;
import com.wav.desafio.model.dto.SessionDTO;
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
@DisplayName("Session Controller")
public class SessionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CreatedSessionDTO createdSessionDTO;
    @Mock
    private SessionDTO sessionResponseDTO;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        objectMapper = new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Integer mockedAgendaId = 1;
        Integer mockedDuration = 60;
        LocalDateTime mocekdCreatedAt = LocalDateTime.of(2024, 04, 28, 07, 00, 00);

        createdSessionDTO = CreatedSessionDTO.builder()
                                             .agendaId(mockedAgendaId)
                                             .duration(mockedDuration)
                                             .build();

        sessionResponseDTO = SessionDTO.builder()
                                               .id(1)
                                               .agenda_id(mockedAgendaId)
                                               .duration(mockedDuration)
                                               .createdAt(mocekdCreatedAt)
                                               .build();
    }

    @Test
        @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql" ),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Open Session")
    void shouldOpenSession() throws Exception {
        mockMvc.perform(post("/api/v1/session/open").contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                .content(objectMapper.writeValueAsString(createdSessionDTO)))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
    
    @Test
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql" ),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertSession.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Find By Id") 
    void shouldFindById() throws Exception {
        mockMvc.perform(get("/api/v1/session/{sessionId}", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(objectMapper.writeValueAsString(sessionResponseDTO)));
    }

    @Test
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertAgenda.sql" ),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/insertSession.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scripts/resetDB.sql")
    })
    @DisplayName("Should Find All")
    void shouldFindAll() throws Exception {
        mockMvc.perform(get("/api/v1/session/", 1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(content().json(objectMapper.writeValueAsString(List.of(sessionResponseDTO))));
    }
}
