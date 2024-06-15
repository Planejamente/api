package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.planejamente.planejamente.entity.Endereco;
import org.planejamente.planejamente.entity.usuario.UsuarioRole;

import java.time.LocalDate;

@Getter
@Setter
public abstract class UsuarioDto {
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotNull
    @Past
    private LocalDate dataDeNascimento;
    @Size(max = 11)
    private String telefone;
    @NotBlank
    @Size(max = 9)
    private String genero;
    @CreationTimestamp
    private LocalDate dtCriacao;
    @NotBlank
    @Email(message = "email inválido")
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(max = 255)
    private String googleSub;
    @OneToOne
    private Endereco endereco;
    @NotNull
    private UsuarioRole role;
}