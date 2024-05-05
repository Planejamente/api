package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.usuario.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByGoogleSubEquals(String googleSub);
}
