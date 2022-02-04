package br.com.api.mimosbyliv.dto;

public class TokenDTO {

    private String token;
    private String tipoDeAutenticacao;
    public TokenDTO(String token, String tipoDeAutenticacao) {
        this.token = token;
        this.tipoDeAutenticacao = tipoDeAutenticacao;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoDeAutenticacao() {
        return tipoDeAutenticacao;
    }
    public void setTipoDeAutenticacao(String tipoDeAutenticacao) {
        this.tipoDeAutenticacao = tipoDeAutenticacao;
    }
}
