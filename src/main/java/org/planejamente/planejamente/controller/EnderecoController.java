//package org.planejamente.planejamente.controller;
//
//import jakarta.validation.Valid;
//import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
//import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
//import org.planejamente.planejamente.entity.Endereco;
//import org.planejamente.planejamente.entity.usuario.Usuario;
//import org.planejamente.planejamente.mapper.EnderecoMapper;
//import org.planejamente.planejamente.repository.EnderecoRepository;
//import org.planejamente.planejamente.repository.UsuarioRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("enderecos")
//public class EnderecoController {
//    private final EnderecoRepository repository;
//    private final UsuarioRepository usuarioRepository;
//
//    public EnderecoController(EnderecoRepository repository, UsuarioRepository usuarioRepository) {
//        this.repository = repository;
//        this.usuarioRepository = usuarioRepository;
//    }
//
//    @GetMapping("/{idUsuario}")
//    public ResponseEntity<EnderecoDtoConsultar> listarPorUsuario(@PathVariable UUID idUsuario) {
//        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
//
//        if(Objects.isNull(usuario)) return ResponseEntity.notFound().build();
//
//        Endereco endereco = usuario.getEndereco();
//
//        if(Objects.isNull(endereco)) return ResponseEntity.noContent().build();
//        EnderecoDtoConsultar dto = EnderecoMapper.toDto(endereco);
//
//        return ResponseEntity.ok(dto);
//    }
//
//    @PostMapping
//    public ResponseEntity<UUID> criar(@RequestBody @Valid EnderecoDto dtoCriacao) {
//        Endereco endereco = EnderecoMapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(endereco)) return ResponseEntity.badRequest().build();
//
//        Endereco enderecoCadastrado = this.repository.save(endereco);
//        UUID idEnderecoCadastrado = enderecoCadastrado.getId();
//
//        return ResponseEntity.status(201).body(idEnderecoCadastrado);
//    }
//}