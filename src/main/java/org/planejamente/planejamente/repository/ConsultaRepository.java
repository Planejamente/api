package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
}
