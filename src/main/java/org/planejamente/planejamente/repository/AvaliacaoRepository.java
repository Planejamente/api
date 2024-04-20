package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
}
