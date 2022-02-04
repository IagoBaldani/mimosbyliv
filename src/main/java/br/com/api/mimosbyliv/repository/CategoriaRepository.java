package br.com.api.mimosbyliv.repository;

import br.com.api.mimosbyliv.model.Categoria;
import br.com.api.mimosbyliv.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByNome(String nome);
}
