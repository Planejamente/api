package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PsicologoDtoExibir {
    private String nome;
    private String crp;
    private Integer idade;
    private Double avaliacao; //
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String cep;
    private String cpf;
    private String cnpj;
    private String genero;
    private Integer qtdAtendimentos;
    private String descricao;
    private String estado;
    private String fundo;
    private String fotoPerfil;
    private List<PsicologoDtoExibir.Especialidade> especialidade;
    private List<PsicologoDtoExibir.ExperienciasFormacoes> experienciasFormacoes;

    @Data
    public static class Especialidade {
        private String titulo;
        private String descricao;
    }

    @Data
    public static class ExperienciasFormacoes {
        private LocalDate dataInicio;
        private LocalDate dataFim;
        private String tipo;
        private String instituicao;
        private String cargo;
        private String descricao;
        private String titulo;
    }
}