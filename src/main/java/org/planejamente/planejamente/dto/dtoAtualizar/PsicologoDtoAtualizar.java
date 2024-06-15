package org.planejamente.planejamente.dto.dtoAtualizar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PsicologoDtoAtualizar {
    @Nullable
    @Size(max = 255)
    private String nome;
    @Nullable
    @Size(max = 11)
    private String telefone;
    @Nullable
    @Size(max = 9)
    private String genero;
    @Nullable
    @Size(max = 255)
    private String descricao;
}