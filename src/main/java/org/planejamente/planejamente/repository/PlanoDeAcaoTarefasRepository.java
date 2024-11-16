package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanoDeAcaoTarefasRepository extends JpaRepository<PlanoDeAcaoTarefas, UUID> {
    Optional<PlanoDeAcaoTarefas> findById(UUID id);
}
