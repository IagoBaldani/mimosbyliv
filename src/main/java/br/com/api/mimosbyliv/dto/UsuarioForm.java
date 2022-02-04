package br.com.api.mimosbyliv.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioForm {
    private String nome;
    private String senha;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(nome, senha);
    }
}
