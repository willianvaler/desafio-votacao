package com.wav.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDTO
{
    private Integer id;
    private Integer agenda_id;
    private Integer duration; //in minutes
    private LocalDateTime createdAt;
}
