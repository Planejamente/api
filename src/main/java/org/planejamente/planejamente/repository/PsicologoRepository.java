package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PsicologoRepository extends JpaRepository<Psicologo, UUID> {
    List<Psicologo> findByGenero(String genero);
}