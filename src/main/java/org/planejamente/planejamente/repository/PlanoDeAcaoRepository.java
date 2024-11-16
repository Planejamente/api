package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.PlanoDeAcao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlanoDeAcaoRepository extends JpaRepository<PlanoDeAcao, UUID> {
    List<PlanoDeAcao> findByConsulta_Id(UUID consultaId);
    List<PlanoDeAcao> findByConsulta_Paciente_IdOrConsulta_Psicologo_Id(UUID consultaPaciente, UUID consultaPsicologo);
}
