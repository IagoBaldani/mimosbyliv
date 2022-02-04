package br.com.api.mimosbyliv.dto;

import br.com.api.mimosbyliv.exceptions.NaoAchadoException;
import br.com.api.mimosbyliv.model.Categoria;
import br.com.api.mimosbyliv.model.Produto;
import br.com.api.mimosbyliv.repository.CategoriaRepository;
import br.com.api.mimosbyliv.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class

ProdutoDTO {
    private BigDecimal preco;
    private String categoria;
    private String tamanho;
    private String descricao;
    private String urlDaImagem;

    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlDaImagem() {
        return urlDaImagem;
    }
    public void setUrlDaImagem(String urlDaImagem) {
        this.urlDaImagem = urlDaImagem;
    }

    public Produto converte(CategoriaRepository categoriaRepository){
        Categoria categoria = categoriaRepository.findByNome(this.categoria);
        Produto produto = new Produto();

        produto.setCategoria(categoria);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        produto.setTamanhos(this.tamanho);
        produto.setUrlImagem(this.urlDaImagem);
        produto.setStatus("Disponível");

        return produto;
    }

    public void atualizar(Integer id, ProdutoRepository repository, CategoriaRepository categoriaRepository) throws Exception {
        Optional<Produto> optProduto = repository.findById(id);
        Categoria categoria = categoriaRepository.findByNome(this.categoria);

        if(optProduto.isPresent()){
            Produto produto = optProduto.get();

            produto.setCategoria(categoria);
            produto.setDescricao(this.descricao);
            produto.setPreco(this.preco);
            produto.setTamanhos(this.tamanho);
            produto.setUrlImagem(this.urlDaImagem);

            repository.save(produto);
        }
        else {
            throw new NaoAchadoException("Não há nenhum produto cadastrado com este ID!");
        }
    }
}
