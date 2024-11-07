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
public class VoteResultDTO
{
    private String name;
    private String description;
    private String result;

    private Long proVotes;
    private Long againstVotes;

    public VoteResultDTO( Long proVotes, Long againstVotes )
    {
        this.proVotes = proVotes;
        this.againstVotes = againstVotes;
    }

    public String getResult()
    {
        if ( proVotes > againstVotes )
        {
            return "Aprovada";
        }

        else if ( againstVotes > proVotes )
        {
            return "Reprovada";
        }

        else
        {
            return "Empate";
        }
    }
}
