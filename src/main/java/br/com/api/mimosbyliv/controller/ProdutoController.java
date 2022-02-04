package br.com.api.mimosbyliv.controller;

import br.com.api.mimosbyliv.dto.ProdutoDTO;
import br.com.api.mimosbyliv.model.Produto;
import br.com.api.mimosbyliv.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class ProdutoController {

    @Autowired
    ProdutoService service;

    private static final String NAO_ENCONTRADO_EXCEPTION = "br.com.api.mimosbyliv.exceptions.NaoAchadoException";

    @GetMapping("/produtos/{idCategoria}")
    public ResponseEntity retornaProdutosPorCategoria(@PathVariable Integer idCategoria){
        try{
            List<Produto> produtosAchados = service.getPorCategoria(idCategoria);
            return ResponseEntity.ok().body(produtosAchados);
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaProdutosPorId(@PathVariable Integer id){
        try{
            Produto produto = service.getPorId(id);
            return ResponseEntity.ok().body(produto);
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping
    public ResponseEntity cadastraProduto(@RequestBody ProdutoDTO produto){
        try{
            service.cadastraProduto(produto);
            return ResponseEntity.ok().body("Produto cadastrado com sucesso!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizaProduto(@RequestBody ProdutoDTO produto, @PathVariable Integer id){
        try{
            service.atualizaProduto(produto, id);
            return ResponseEntity.ok().body("Produto atualizado com sucesso!");
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity atualizaStatusProduto(@PathVariable Integer id){
        try{
            service.atualizaStatus(id);
            return ResponseEntity.ok().body("Status do produto atualizado com sucesso!");
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaProduto(@PathVariable Integer id){
        try{
            service.deletarProduto(id);
            return ResponseEntity.ok().body("Produto deletado com sucesso!");
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
