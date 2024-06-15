package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, UUID> {
    List<Especialidade> findAllByPsicologoId(UUID idPsicologo);
    Boolean existsByTituloIgnoreCase(String titulo);
}
