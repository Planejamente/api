package org.planejamente.planejamenteapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity(name = "psicologo")
public class Psicologo {
    @Id
    public UUID uuid;
    public String nome;
    public String crp;
    public String dataNascimento;
    public String genero;

    public Psicologo() {
    }

    public Psicologo(UUID uuid, String nome, String crp, String dataNascimento, String genero) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.crp = crp;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }
}
