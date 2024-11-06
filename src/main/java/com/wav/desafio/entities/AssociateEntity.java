package com.wav.desafio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "associates")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Entidade do Associado
 */
public class AssociateEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotBlank(message = "the field name must not be empty or null")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "the field cpf must not be empty or null")
    @Column(nullable = false)
    private String cpf;
}
