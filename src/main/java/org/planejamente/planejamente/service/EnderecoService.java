package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
import org.planejamente.planejamente.entity.Endereco;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.mapper.EnderecoMapper;
import org.planejamente.planejamente.repository.EnderecoRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EnderecoDtoConsultar salvar(EnderecoDto dto) {
        Usuario usuario = this.usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if(this.enderecoRepository.existsByUsuarioId(dto.getIdUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já possui um endereço");
        }

        Endereco endereco = EnderecoMapper.toEntity(dto);
        endereco.setUsuario(usuario);

        Endereco enderecoSalvo = this.enderecoRepository.save(endereco);
        return EnderecoMapper.toDto(enderecoSalvo);
    }

    public EnderecoDtoConsultar buscarPorId(UUID idUsuario) {
        Endereco endereco = this.enderecoRepository.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return EnderecoMapper.toDto(endereco);
    }

    public EnderecoDtoConsultar atualizar(String id, String cep) {
        Endereco endereco = this.enderecoRepository.findByUsuarioId(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        endereco.setCep(cep);
        Endereco enderecoAtualizado = this.enderecoRepository.save(endereco);

        return EnderecoMapper.toDto(enderecoAtualizado);
    }
}