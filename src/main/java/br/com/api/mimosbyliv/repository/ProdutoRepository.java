package br.com.api.mimosbyliv.repository;

import br.com.api.mimosbyliv.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "SELECT * FROM Produto as p WHERE p.categoria_id = :idCategoria ORDER BY p.status", nativeQuery = true)
    List<Produto> findAllByCategoria(@Param("idCategoria") Integer categoria);

    @Query(value = "SELECT * FROM Produto as p WHERE p.mais_vendidos = 'S' ORDER BY p.status", nativeQuery = true)
    List<Produto> findMaisVendidos();
}
