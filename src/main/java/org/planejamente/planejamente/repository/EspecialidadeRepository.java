package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, UUID> {
}
