package com.wav.desafio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Entidade do Voto
 */
public class VoteEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean vote;

    @Column( name = "associate_id", nullable = false, insertable = false, updatable = false )
    private Integer associateId;

    @Column( name = "session_id", nullable = false, insertable = false, updatable = false )
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(name = "associate_id", insertable = false, updatable = false)
    private AssociateEntity associateEntity;

    @ManyToOne
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    private SessionEntity sessionEntity;
}
