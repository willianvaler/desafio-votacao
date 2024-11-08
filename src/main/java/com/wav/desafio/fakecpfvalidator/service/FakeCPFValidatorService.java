package com.wav.desafio.fakecpfvalidator.service;

import com.wav.desafio.fakecpfvalidator.dto.FakeCPFDTO;
import com.wav.desafio.utils.CPFUtilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FakeCPFValidatorService
{
    
    public ResponseEntity<FakeCPFDTO> isCpfValid( String cpf)
    {
        boolean isCpfValid = CPFUtilities.randomValidCpf(cpf);

        if (isCpfValid)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FakeCPFDTO.builder()
                                                                              .status("CPF Inválido")
                                                                              .build());
        }

        boolean isCpfAbleToVote = CPFUtilities.randomValidCpf(cpf);
        
        if (isCpfAbleToVote)
        {
            return ResponseEntity.ok().body(FakeCPFDTO.builder()
                                                      .status("ABLE_TO_VOTE")
                                                      .build());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(FakeCPFDTO.builder()
                                                                          .status("UNABLE_TO_VOTE")
                                                                          .build());
    }
}
