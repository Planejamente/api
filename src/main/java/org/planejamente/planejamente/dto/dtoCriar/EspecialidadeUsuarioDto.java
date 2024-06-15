package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EspecialidadeUsuarioDto {
    @NotNull
    private UUID idPsicologo;
    @NotNull
    private List<UUID> listaIdEspecialidade;
}