package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PsicologoRepository extends JpaRepository<Psicologo, UUID> {
    List<Psicologo> findByGenero(String genero);
    Psicologo findPsicologoByIdIs(UUID id);

    @Query("SELECT p.nome FROM Psicologo p WHERE p.id = :id")
    String findNomeById(@Param("id") UUID id);
}