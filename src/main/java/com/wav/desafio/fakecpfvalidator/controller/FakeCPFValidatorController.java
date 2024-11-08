package com.wav.desafio.fakecpfvalidator.controller;

import com.wav.desafio.fakecpfvalidator.dto.FakeCPFDTO;
import com.wav.desafio.fakecpfvalidator.service.FakeCPFValidatorService;
import com.wav.desafio.model.CPFValidatorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Tag(name = "CPF-Client", description = "Simulação de API de CPF para consumir pelo facade")
public class FakeCPFValidatorController
{

    private final FakeCPFValidatorService fakeCPFValidatorService;
    
    @PostMapping("/cpf/validate/{cpf}")
    @Operation(
            summary = "Validar CPF",
            description = "Realiza a validação randomicamente se um CPF é valido ou não"
    )
    @ApiResponses(
    {
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CPFValidatorDTO.class))
        })
    })
    public ResponseEntity<FakeCPFDTO> validateCpf( @PathVariable String cpf)
    {
        return fakeCPFValidatorService.isCpfValid(cpf);
    }
}
