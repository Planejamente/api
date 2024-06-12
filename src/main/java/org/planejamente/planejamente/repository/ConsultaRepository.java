package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    Boolean existsByPsicologoIdAndInicioBetween(UUID id, LocalDateTime inicio, LocalDateTime fim);
    Boolean existsByPsicologoIdAndFimBetween(UUID id, LocalDateTime inicio, LocalDateTime fim);
    Integer countConsultaByPsicologoId(UUID idPsicologo);
    List<Consulta> findAllByPsicologoIdAndFimBefore(UUID idPsicologo, LocalDateTime data);
    Integer countConsultaByPsicologoIdAndFimBefore(UUID idPsicologo, LocalDateTime data);
}