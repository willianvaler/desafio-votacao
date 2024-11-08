package com.wav.desafio.model.dto;

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
public class VoteDTO
{
    private Integer id;
    private boolean vote;
    private Integer associate_id;
    private Integer session_id;
}
