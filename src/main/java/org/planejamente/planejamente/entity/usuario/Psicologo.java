package org.planejamente.planejamente.entity.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Endereco;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.ExperienciaFormacao;

import java.util.List;

@Entity
@Getter
@Setter
public class Psicologo extends Usuario {
    private String crp;
    private String cnpj;
    private String cpf;
    private String linkFotoPerfil;
    private String idCalendarioDisponivel;
    private String idCalendarioConsulta;
    private String linkAnamnese;
    private String idAnamnese;
    private String linkFotoDeFundo;
    private String descricao;
    private String headline;
    @OneToMany(mappedBy = "psicologo")
    private List<Especialidade> especialidades;
    @OneToMany(mappedBy = "psicologo")
    private List<ExperienciaFormacao> experienciaFormacoes;
    @OneToOne
    private Endereco endereco;
}