//package org.planejamente.planejamente.controller;
//
//import jakarta.validation.Valid;
//import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeUsuarioDtoConsultar;
//import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeUsuarioDto;
//import org.planejamente.planejamente.entity.Especialidade;
//import org.planejamente.planejamente.entity.EspecialidadeUsuario;
//import org.planejamente.planejamente.entity.usuario.Psicologo;
//import org.planejamente.planejamente.mapper.EspecialidadeUsuarioMapper;
//import org.planejamente.planejamente.repository.EspecialidadeRepository;
//import org.planejamente.planejamente.repository.EspecialidadeUsuarioRepository;
//import org.planejamente.planejamente.repository.PsicologoRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/especialidades-usuarios")
//public class EspecialidadeUsuarioController {
//    private final EspecialidadeUsuarioRepository repository;
//    private final PsicologoRepository psicologoRepository;
//    private final EspecialidadeRepository especialidadeRepository;
//
//    public EspecialidadeUsuarioController(EspecialidadeUsuarioRepository repository,
//                                          PsicologoRepository psicologoRepository,
//                                          EspecialidadeRepository especialidadeRepository) {
//        this.repository = repository;
//        this.psicologoRepository = psicologoRepository;
//        this.especialidadeRepository = especialidadeRepository;
//    }
//
//    @GetMapping("/psicologo/{idPsicologo}")
//    public ResponseEntity<List<EspecialidadeUsuarioDtoConsultar>> listarPorPsicologo(@PathVariable UUID idPsicologo) {
//        Psicologo psicologo = this.psicologoRepository.findById(idPsicologo).orElse(null);
//
//        if(psicologo == null) return ResponseEntity.notFound().build();
//
//        List<EspecialidadeUsuario> especialidadesUsuario = this.repository.findByPsicologo_IdEquals(psicologo.getId());
//
//        if(especialidadesUsuario.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<EspecialidadeUsuarioDtoConsultar> dto = EspecialidadeUsuarioMapper.toDto(especialidadesUsuario);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/especialidade/{idEspecialidade}")
//    public ResponseEntity<List<EspecialidadeUsuarioDtoConsultar>> listarPorEspecialidade(@PathVariable UUID idEspecialidade) {
//        Especialidade especialidade = this.especialidadeRepository.findById(idEspecialidade).orElse(null);
//
//        if(especialidade == null) return ResponseEntity.notFound().build();
//
//        List<EspecialidadeUsuario> especialidadeUsuarios = this.repository.findByEspecialidade_IdEquals(especialidade.getId());
//
//        if(especialidadeUsuarios.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<EspecialidadeUsuarioDtoConsultar> dto = EspecialidadeUsuarioMapper.toDto(especialidadeUsuarios);
//        return ResponseEntity.ok(dto);
//    }
//
//    @PostMapping
//    public ResponseEntity<EspecialidadeUsuarioDtoConsultar> criar(@RequestBody @Valid EspecialidadeUsuarioDto dtoCriacao) {
//        EspecialidadeUsuario especialidadeUsuario = EspecialidadeUsuarioMapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(especialidadeUsuario)) return ResponseEntity.badRequest().build();
//
//        UUID idPsicologoCriacao = especialidadeUsuario.getPsicologo().getId();
//        UUID idEspecialidadeCriacao = especialidadeUsuario.getEspecialidade().getId();
//
//        Psicologo psicologo = this.psicologoRepository.findById(idPsicologoCriacao).orElse(null);
//        Especialidade especialidade = this.especialidadeRepository.findById(idEspecialidadeCriacao).orElse(null);
//
//        if(Objects.isNull(psicologo)) return ResponseEntity.notFound().build();
//        if(Objects.isNull(especialidade)) return ResponseEntity.notFound().build();
//
//        EspecialidadeUsuario especialidadeUsuarioCadastrada = this.repository.save(especialidadeUsuario);
//        EspecialidadeUsuarioDtoConsultar dto = EspecialidadeUsuarioMapper.toDto(especialidadeUsuarioCadastrada);
//
//        return ResponseEntity.status(201).body(dto);
//    }
//
//    @DeleteMapping("/{idEspecialidade}/{idPsicologo}")
//    public ResponseEntity<Void> deletar(@PathVariable UUID idEspecialidade, @PathVariable UUID idPsicologo) {
//        Especialidade especialidade = this.especialidadeRepository.findById(idEspecialidade).orElse(null);
//        Psicologo psicologo = this.psicologoRepository.findById(idPsicologo).orElse(null);
//
//        if(Objects.isNull(especialidade)) return ResponseEntity.notFound().build();
//        if(Objects.isNull(psicologo)) return ResponseEntity.notFound().build();
//
//        UUID idEspecialidadeEncontrada = especialidade.getId();
//        UUID idPsicologoEncontrado = psicologo.getId();
//
//        EspecialidadeUsuario especialidadeUsuario = this.repository.findByEspecialidade_IdAndPsicologo_IdEquals(idEspecialidadeEncontrada,
//                idPsicologoEncontrado).orElse(null);
//
//        if(especialidadeUsuario == null) return ResponseEntity.notFound().build();
//
//        this.repository.delete(especialidadeUsuario);
//        return ResponseEntity.noContent().build();
//    }
//}