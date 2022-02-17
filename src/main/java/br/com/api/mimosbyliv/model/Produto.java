package br.com.api.mimosbyliv.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Categoria categoria;
    private BigDecimal preco;
    private String tamanhos;
    private String urlImagem;
    private String descricao;
    private String status;
    private String maisVendidos;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTamanhos() {
        return tamanhos;
    }
    public void setTamanhos(String tamanhos) {
        this.tamanhos = tamanhos;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaisVendidos() {
        return maisVendidos;
    }
    public void setMaisVendidos(String maisVendidos) {
        this.maisVendidos = maisVendidos;
    }
}
