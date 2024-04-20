package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
