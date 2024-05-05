//package org.planejamente.planejamente.repository;
//
//import org.planejamente.planejamente.entity.EspecialidadeUsuario;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public interface EspecialidadeUsuarioRepository extends JpaRepository<EspecialidadeUsuario, UUID> {
//    List<EspecialidadeUsuario> findByPsicologo_IdEquals(UUID id);
//    List<EspecialidadeUsuario> findByEspecialidade_IdEquals(UUID id);
//    Optional<EspecialidadeUsuario> findByEspecialidade_IdAndPsicologo_IdEquals(UUID idPsicologo, UUID idEspecialidade);
//}