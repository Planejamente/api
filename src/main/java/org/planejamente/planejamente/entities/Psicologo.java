package org.planejamente.planejamente.entities;

public class Psicologo extends Usuario {
    private String crp;

    public Psicologo(String nome, String email, String senha, String crp) {
        super(nome, email, senha);
        this.crp = crp;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }
}
