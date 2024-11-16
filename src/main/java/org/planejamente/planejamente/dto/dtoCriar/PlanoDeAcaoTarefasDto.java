package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlanoDeAcaoTarefasDto {
    @NotBlank
    @Size(max = 255)
    private String idTarefa;
    @NotBlank
    @Size(max = 255)
    private String predecessor;
    @NotBlank
    @Size(max = 255)
    private String tarefa;
    @NotBlank
    @Size(max = 255)
    private String status;
    @NotNull
    private UUID planoDeAcao;
}
