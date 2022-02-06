package br.com.api.mimosbyliv.service;


import br.com.api.mimosbyliv.exceptions.NaoAchadoException;
import br.com.api.mimosbyliv.model.Categoria;
import br.com.api.mimosbyliv.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listaCategorias() throws Exception {
        final List<Categoria> categorias = repository.findAll();

        if(!categorias.isEmpty()){
            return categorias;
        }

        throw new NaoAchadoException("Não há nenhuma categoria cadaastrada!");
    }
}
