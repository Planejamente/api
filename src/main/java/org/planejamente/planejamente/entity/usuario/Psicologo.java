package org.planejamente.planejamente.entity.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Psicologo extends Usuario {
    private String crp;
    private String cnpj;
    private String cpf;
    private String linkFotoPerfil;
    private String idCalendarioHorarioDeTrabalho;
    private String idCalendarioConsulta;
    private String linkAnamnese;
    private String idAnamnese;
    private String linkFotoDeFundo;
    private String descricao;
    private String headline;
}