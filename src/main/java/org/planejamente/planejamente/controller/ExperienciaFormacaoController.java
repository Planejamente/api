//package org.planejamente.planejamente.controller;
//
//import jakarta.validation.Valid;
//import org.planejamente.planejamente.dto.dtoConsultar.ExperienciaFormacaoDtoConsultar;
//import org.planejamente.planejamente.dto.dtoCriar.ExperienciaFormacaoDto;
//import org.planejamente.planejamente.entity.ExperienciaFormacao;
//import org.planejamente.planejamente.entity.usuario.Psicologo;
//import org.planejamente.planejamente.mapper.ExperienciaFormacaoMapper;
//import org.planejamente.planejamente.repository.ExperienciaFormacaoRepository;
//import org.planejamente.planejamente.repository.PsicologoRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/experiencias-formacoes")
//public class ExperienciaFormacaoController implements IMetodosGenericos<ExperienciaFormacaoDtoConsultar, ExperienciaFormacaoDto> {
//    private final ExperienciaFormacaoRepository repository;
//    private final PsicologoRepository psicologoRepository;
//
//    public ExperienciaFormacaoController(ExperienciaFormacaoRepository repository, PsicologoRepository psicologoRepository) {
//        this.repository = repository;
//        this.psicologoRepository = psicologoRepository;
//    }
//
//    @Override
//        @GetMapping
//    public ResponseEntity<List<ExperienciaFormacaoDtoConsultar>> listar() {
//        List<ExperienciaFormacao> experienciasFormacaos = this.repository.findAll();
//
//        if(experienciasFormacaos.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<ExperienciaFormacaoDtoConsultar> dto = ExperienciaFormacaoMapper.toDto(experienciasFormacaos);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @GetMapping("/{id}")
//    public ResponseEntity<ExperienciaFormacaoDtoConsultar> listarPorId(@PathVariable UUID id) {
//        ExperienciaFormacao experienciaFormacao = this.repository.findById(id).orElse(null);
//
//        if(experienciaFormacao == null) return ResponseEntity.notFound().build();
//
//        ExperienciaFormacaoDtoConsultar dto = ExperienciaFormacaoMapper.toDto(experienciaFormacao);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/psicologo/{idPsicologo}")
//    public ResponseEntity<List<ExperienciaFormacaoDtoConsultar>> listarPorPsicologo(@PathVariable UUID idPsicologo) {
//        Psicologo psicologo = this.psicologoRepository.findById(idPsicologo).orElse(null);
//
//        if(psicologo == null) return ResponseEntity.notFound().build();
//
//        List<ExperienciaFormacao> experienciasFormacoes = this.repository.findByPsicologo_IdEquals(psicologo.getId());
//
//        if(experienciasFormacoes.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<ExperienciaFormacaoDtoConsultar> dto = ExperienciaFormacaoMapper.toDto(experienciasFormacoes);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @PostMapping
//    public ResponseEntity<ExperienciaFormacaoDtoConsultar> criar(@RequestBody @Valid ExperienciaFormacaoDto dtoCriacao) {
//        ExperienciaFormacao experienciaFormacao = ExperienciaFormacaoMapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(experienciaFormacao)) return ResponseEntity.badRequest().build();
//
//        ExperienciaFormacao experienciaFormacaoCadastrada = this.repository.save(experienciaFormacao);
//        ExperienciaFormacaoDtoConsultar dto = ExperienciaFormacaoMapper.toDto(experienciaFormacaoCadastrada);
//
//        return ResponseEntity.status(201).body(dto);
//    }
//
//    @Override
//        @PutMapping("/{id}")
//    public ResponseEntity<ExperienciaFormacaoDtoConsultar> atualizar(@RequestBody @Valid ExperienciaFormacaoDto dto,
//                                                                     @PathVariable UUID id) {
//        return null;
//    }
//
//    @Override
//        @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
//        ExperienciaFormacao experienciaFormacao = this.repository.findById(id).orElse(null);
//
//        if(experienciaFormacao == null) return ResponseEntity.notFound().build();
//
//        this.repository.delete(experienciaFormacao);
//        return ResponseEntity.noContent().build();
//    }
//}