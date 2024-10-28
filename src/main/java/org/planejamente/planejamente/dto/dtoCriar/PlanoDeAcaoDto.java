package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlanoDeAcaoDto {
    @NotBlank
    @Size(max = 255)
    private String tituloPlanoDeAcao;
    @Nullable
    private UUID idConsulta;
    @Nullable
    private List<PlanoDeAcaoTarefas> tarefas;
}
