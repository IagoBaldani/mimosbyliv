package br.com.api.mimosbyliv.service;

import br.com.api.mimosbyliv.dto.ProdutoDTO;
import br.com.api.mimosbyliv.exceptions.NaoAchadoException;
import br.com.api.mimosbyliv.model.Produto;
import br.com.api.mimosbyliv.repository.CategoriaRepository;
import br.com.api.mimosbyliv.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Produto getPorId(Integer id) throws Exception{
        final Optional<Produto> optionalProduto = repository.findById(id);

        if(optionalProduto.isPresent()){
            return optionalProduto.get();
        }

        throw new NaoAchadoException("Não há nenhum produto cadastrado com este ID!");
    }

    public void cadastraProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoDTO.converte(categoriaRepository);
        repository.save(produto);
    }

    public void atualizaProduto(ProdutoDTO produtoDTO, Integer id) throws Exception {
        produtoDTO.atualizar(id, repository, categoriaRepository);
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

}
