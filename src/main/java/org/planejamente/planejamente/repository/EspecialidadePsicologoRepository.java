package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.EspecialidadePsicologo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface EspecialidadePsicologoRepository extends JpaRepository<EspecialidadePsicologo, UUID> {
}
