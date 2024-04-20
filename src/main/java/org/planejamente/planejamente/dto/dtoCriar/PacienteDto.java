package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Acesso;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteDto {
    @NotBlank
        @Size(max = 255)
    private String nome;
    @NotNull
        @Past
    private LocalDate dataDeNascimento;
    @NotBlank
        @Size(max = 255)
    private String telefone;
    @NotBlank
        @Size(max = 255)
    private String genero;
    @OneToOne
        @NotNull
    private Acesso acesso;
}
