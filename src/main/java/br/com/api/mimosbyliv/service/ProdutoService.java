package br.com.api.mimosbyliv.service;

import br.com.api.mimosbyliv.dto.ProdutoDTO;
import br.com.api.mimosbyliv.exceptions.NaoAchadoException;
import br.com.api.mimosbyliv.model.Produto;
import br.com.api.mimosbyliv.repository.CategoriaRepository;
import br.com.api.mimosbyliv.repository.ProdutoRepository;
import br.com.api.mimosbyliv.util.ExceptionThrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> getPorCategoria(Integer idCategoria) throws Exception{
        final List<Produto> allByCategoria = repository.findAllByCategoria(idCategoria);

        if(!allByCategoria.isEmpty()){
            return allByCategoria;
        }

        throw new NaoAchadoException("Não há nenhum produto cadastrado nesta categoria!");
    }

    public List<Produto> getMaisVendidos() throws Exception{
        final List<Produto> maisVendidos = repository.findMaisVendidos();

        if(!maisVendidos.isEmpty()){
            return maisVendidos;
        }

        throw new NaoAchadoException("Não há nenhum produto cadastrado nesta categoria!");
    }

    public Produto getPorId(Integer id) throws Exception{
        final Optional<Produto> optionalProduto = repository.findById(id);

        if(optionalProduto.isPresent()){
            return optionalProduto.get();
        }

        throw new NaoAchadoException("Não há nenhum produto cadastrado com este ID!");
    }

    public void cadastraProduto(ProdutoDTO produtoDTO) throws Exception{
        HashMap<String, String> verificacao = verificacaoDeCampos(produtoDTO);

        if(verificacao == null){
            Produto produto = produtoDTO.converte(categoriaRepository);
            repository.save(produto);
        }
        else{
            ExceptionThrower.thrower(verificacao, "preco");
            ExceptionThrower.thrower(verificacao, "categoria");
            ExceptionThrower.thrower(verificacao, "url");
            ExceptionThrower.thrower(verificacao, "maisVendidos");

            if(verificacao.containsKey("tamanho")){
                if(produtoDTO.getCategoria().equals("roupas")){
                    ExceptionThrower.thrower(verificacao, "tamanho");
                }
                produtoDTO.setTamanho("");
            }
            if(verificacao.containsKey("descricao")){
                produtoDTO.setDescricao("");
            }

            Produto produto = produtoDTO.converte(categoriaRepository);
            repository.save(produto);
        }
    }

    public void atualizaProduto(ProdutoDTO produtoDTO, Integer id) throws Exception {
        HashMap<String, String> verificacao = verificacaoDeCampos(produtoDTO);

        if(verificacao == null){
            Produto produto = produtoDTO.atualizar(id, repository, categoriaRepository);
            repository.save(produto);
        }
        else{
            ExceptionThrower.thrower(verificacao, "preco");
            ExceptionThrower.thrower(verificacao, "categoria");
            ExceptionThrower.thrower(verificacao, "url");
            ExceptionThrower.thrower(verificacao, "maisVendidos");

            if(verificacao.containsKey("tamanho")){
                if(produtoDTO.getCategoria().equals("roupas")){
                    ExceptionThrower.thrower(verificacao, "tamanho");
                }

                produtoDTO.setTamanho("");
            }
            if(verificacao.containsKey("descricao")){
                produtoDTO.setDescricao("");
            }

            Produto produto = produtoDTO.atualizar(id, repository, categoriaRepository);
            repository.save(produto);
        }
    }

    public void atualizaStatus(Integer id) throws Exception{
        final Optional<Produto> optionalProduto = repository.findById(id);

        if(optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            if (produto.getStatus().equals("Disponível")) {
                produto.setStatus("Esgotado");
            }
            else {
                produto.setStatus("Disponível");
            }

            repository.save(produto);
        }
        else{
            throw new NaoAchadoException("Não há nenhum produto cadastrado com este ID!");
        }
    }

    public void deletarProduto(Integer id) throws Exception{
        final Optional<Produto> optionalProduto = repository.findById(id);

        if(optionalProduto.isPresent()){
            repository.delete(optionalProduto.get());
        }
        else{
            throw new NaoAchadoException("Não há nenhum produto cadastrado com este ID!");
        }
    }

    public HashMap<String, String> verificacaoDeCampos(ProdutoDTO produtoDTO){
        HashMap<String, String> erros = new HashMap<>();

        if(produtoDTO.getPreco() == null || produtoDTO.getPreco().equals("")){
            erros.put("preco", "O preço não pode ser vazio ou nulo");
        }
        if(produtoDTO.getCategoria() == null || produtoDTO.getCategoria().equals("")){
            erros.put("categoria","A categoria não pode ser vazia ou nula");
        }
        if(produtoDTO.getUrlDaImagem() == null || produtoDTO.getUrlDaImagem().equals("")){
            erros.put("url","A URL da imagem não pode ser vazia ou nula");
        }
        if(produtoDTO.getMaisVendidos() == null || produtoDTO.getMaisVendidos().equals("")){
            erros.put("maisVendidos","O campo 'MaisVendidos' não pode ser vazio ou nulo");
        }

        if(produtoDTO.getTamanho() == null || produtoDTO.getTamanho().equals("")){
            erros.put("tamanho","O tamanho não pode ser vazio em produtos da categoria 'Roupas'");
        }
        if(produtoDTO.getDescricao() == null || produtoDTO.getDescricao().equals("")){
            erros.put("descricao","A descrição não pode ser nula");
        }

        if(erros.isEmpty()){
            return null;
        }
        return erros;
    }


}
