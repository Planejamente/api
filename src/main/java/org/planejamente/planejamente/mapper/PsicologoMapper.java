package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoAtualizar.PsicologoDtoAtualizar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoExibir;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.ExperienciaFormacao;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PsicologoMapper extends UsuarioMapper<Psicologo, PsicologoDto> {

    @Override
    public Psicologo toEntity(PsicologoDto dto) {
        if(Objects.isNull(dto)) return null;

        Psicologo psicologo = super.toEntity(dto);
        psicologo.setCrp(dto.getCrp());
        psicologo.setCnpj(dto.getCnpj());
        psicologo.setCpf(dto.getCpf());
        psicologo.setLinkFotoPerfil(dto.getLinkFotoPerfil());
        psicologo.setIdCalendarioDisponivel(dto.getIdCalendarioDisponivel());
        psicologo.setIdCalendarioConsulta(dto.getIdCalendarioConsulta());
        psicologo.setLinkAnamnese(dto.getLinkAnamnese());
        psicologo.setIdAnamnese(dto.getIdAnamnese());
        psicologo.setLinkFotoDeFundo(dto.getLinkFotoDeFundo());
        psicologo.setEndereco(dto.getEndereco());
        psicologo.setDescricao(dto.getDescricao());

        return psicologo;
    }

    public static PsicologoDtoConsultar toDto(Psicologo psicologo) {
        if(Objects.isNull(psicologo)) return null;

        PsicologoDtoConsultar dto = new PsicologoDtoConsultar();
        dto.setId(psicologo.getId());
        dto.setNome(psicologo.getNome());
        dto.setTelefone(psicologo.getTelefone());
        dto.setGenero(psicologo.getGenero());
        dto.setEmail(psicologo.getEmail());
        dto.setIdCalendarioDisponivel(psicologo.getIdCalendarioDisponivel());
        dto.setIdCalendarioConsulta(psicologo.getIdCalendarioConsulta());
        dto.setDescricao(psicologo.getDescricao());

        return dto;
    }

    public static List<PsicologoDtoConsultar> toDto(List<Psicologo> psicologos) {
        return psicologos.stream().map(PsicologoMapper::toDto).toList();
    }

    public static PsicologoDtoExibir toDtoCompleta(Psicologo psicologo) {
        if(Objects.isNull(psicologo)) return null;

        PsicologoDtoExibir dto = new PsicologoDtoExibir();

        dto.setNome(psicologo.getNome());
        dto.setCrp(psicologo.getCrp());
        dto.setDescricao(psicologo.getDescricao());
        dto.setIdade(LocalDate.now().getYear() - psicologo.getDataDeNascimento().getYear());
        dto.setFotoPerfil(psicologo.getLinkFotoPerfil());
        dto.setFundo(psicologo.getLinkFotoDeFundo());
        dto.setEstado(psicologo.getEndereco().getEstado());

        List<PsicologoDtoExibir.Especialidade> especialidadesDto = new ArrayList<>();
        List<Especialidade> especialidadesEntidade = psicologo.getEspecialidades();

        if(!especialidadesEntidade.isEmpty()) {
            for (Especialidade especialidade : especialidadesEntidade) {
                PsicologoDtoExibir.Especialidade dtoVez = new PsicologoDtoExibir.Especialidade();

                dtoVez.setTitulo(especialidade.getTitulo());
                dtoVez.setDescricao(especialidade.getDescricao());

                especialidadesDto.add(dtoVez);
            }
        }
        dto.setEspecialidade(especialidadesDto);

        List<PsicologoDtoExibir.ExperienciasFormacoes> experienciasFormacoesDto = new ArrayList<>();
        List<ExperienciaFormacao> experienciaFormacoesEntity = psicologo.getExperienciaFormacoes();

        if(!experienciaFormacoesEntity.isEmpty()) {
            for (ExperienciaFormacao experienciaFormacao : experienciaFormacoesEntity) {
                PsicologoDtoExibir.ExperienciasFormacoes dtoVez = new PsicologoDtoExibir.ExperienciasFormacoes();

                dtoVez.setDataInicio(experienciaFormacao.getDataInicio());
                dtoVez.setDataFim(experienciaFormacao.getDataFim());
                dtoVez.setTipo(experienciaFormacao.getTipo());
                dtoVez.setInstituicao(experienciaFormacao.getInstituicao());
                dtoVez.setCargo(experienciaFormacao.getCargo());
                dtoVez.setDescricao(experienciaFormacao.getDescricao());
                dtoVez.setTitulo(experienciaFormacao.getTitulo());

                experienciasFormacoesDto.add(dtoVez);
            }
        }
        dto.setExperienciasFormacoes(experienciasFormacoesDto);
        return dto;
    }

    public static Psicologo merge(Psicologo psiAntigo, PsicologoDtoAtualizar atualizacao) {
        if(!Objects.isNull(atualizacao.getNome())) psiAntigo.setNome(atualizacao.getNome());
        if(!Objects.isNull(atualizacao.getTelefone())) psiAntigo.setTelefone(atualizacao.getTelefone());
        if(!Objects.isNull(atualizacao.getGenero())) psiAntigo.setGenero(atualizacao.getGenero());
        if(!Objects.isNull(atualizacao.getDescricao())) psiAntigo.setDescricao(atualizacao.getDescricao());

        return psiAntigo;
    }
}