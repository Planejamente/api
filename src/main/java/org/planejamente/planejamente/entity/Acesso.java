package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Acesso")
@Getter
@Setter
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String senha;
    private String salt;
    private String googleOAuthToken;
    @CreationTimestamp
    private LocalDate dtCriacao;
}
