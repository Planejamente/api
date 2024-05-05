package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    List<Endereco> findByUsuario_IdEquals(UUID id);
}
