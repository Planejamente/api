package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByUsuarioId(UUID id);
    Boolean existsByUsuarioId(UUID usuarioId);
}