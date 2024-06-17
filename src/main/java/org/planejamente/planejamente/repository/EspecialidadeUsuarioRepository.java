package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.EspecialidadeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EspecialidadeUsuarioRepository extends JpaRepository<EspecialidadeUsuario, UUID> {
    List<EspecialidadeUsuario> findAllByPsicologoId(UUID idPsicologo);
    EspecialidadeUsuario findByPsicologoIdAndEspecialidadeTitulo(UUID idPsicologo, String titulo);
}