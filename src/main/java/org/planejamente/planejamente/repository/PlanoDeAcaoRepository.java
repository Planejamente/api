package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.PlanoDeAcao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoDeAcaoRepository extends JpaRepository<PlanoDeAcao, UUID> {
}
