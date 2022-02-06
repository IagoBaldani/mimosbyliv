package br.com.api.mimosbyliv.controller;

import br.com.api.mimosbyliv.dto.ProdutoDTO;
import br.com.api.mimosbyliv.model.Categoria;
import br.com.api.mimosbyliv.model.Produto;
import br.com.api.mimosbyliv.service.CategoriaService;
import br.com.api.mimosbyliv.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    private static final String NAO_ENCONTRADO_EXCEPTION = "br.com.api.mimosbyliv.exceptions.NaoAchadoException";

    @GetMapping()
    public ResponseEntity retornaTodasCategoria(){
        try{
            List<Categoria> categoriasAchadas = service.listaCategorias();
            return ResponseEntity.ok().body(categoriasAchadas);
        }
        catch (Exception e){
            if(e.getClass().getName().equals(NAO_ENCONTRADO_EXCEPTION)){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
