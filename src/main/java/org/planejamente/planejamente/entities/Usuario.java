package org.planejamente.planejamente.entities;

import java.util.UUID;

public abstract class Usuario {
    private UUID uuid;
    private String nome;
    private String email;
    private String senha;


    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
