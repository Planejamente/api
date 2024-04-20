package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Arquivos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArquivosRepository extends JpaRepository<Arquivos, UUID> {
}
