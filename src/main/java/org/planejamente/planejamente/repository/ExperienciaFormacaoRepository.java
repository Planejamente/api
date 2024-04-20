package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.ExperienciaFormacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExperienciaFormacaoRepository extends JpaRepository<ExperienciaFormacao, UUID> {
}
