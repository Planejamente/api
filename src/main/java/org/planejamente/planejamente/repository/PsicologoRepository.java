package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PsicologoRepository extends JpaRepository<Psicologo, UUID> {
}
