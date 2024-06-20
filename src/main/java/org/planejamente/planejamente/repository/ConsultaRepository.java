package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query("SELECT DISTINCT c.paciente.nome FROM Consulta c WHERE c.psicologo.id = :psicologoId AND c.fim BETWEEN :inicioMes AND :fimMes")
    List<String> findNomesPacientesNoMes(@Param("psicologoId") UUID psicologoId, @Param("inicioMes") LocalDateTime inicioMes, @Param("fimMes") LocalDateTime fimMes);
    @Query("SELECT COUNT(DISTINCT c.paciente.id) FROM Consulta c WHERE c.fim BETWEEN :inicioAno AND :fimAno")
    long countTotalPacientesNoAno(@Param("inicioAno") LocalDateTime inicioAno, @Param("fimAno") LocalDateTime fimAno);
    List<Consulta> findAllByPsicologoId(UUID idPsi);
    List<Consulta> findAllByPacienteId(UUID idPac);
}