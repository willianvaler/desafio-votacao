package com.wav.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaDTO
{
    private Integer id;
    private String name;
    private String description;
    private Integer assembly_id;
}