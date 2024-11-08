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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Entidade da Sessão
 */
public class SessionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "agenda_id", insertable = false, updatable = false)
    private AgendaEntity agendaEntity;

    @Column( name = "agenda_id", nullable = false, insertable = false, updatable = false )
    private Integer agendaId;

    @Column( columnDefinition = "integer default 1")
    private Integer duration; //in minutes

    @CreationTimestamp
    private LocalDateTime createdAt;

    public boolean isClosed()
    {
        return LocalDateTime.now().isAfter( createdAt.plusMinutes( duration ) );
    }
}
