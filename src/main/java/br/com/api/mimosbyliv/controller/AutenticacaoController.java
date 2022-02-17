package br.com.api.mimosbyliv.controller;

import br.com.api.mimosbyliv.config.TokenService;
import br.com.api.mimosbyliv.dto.TokenDTO;
import br.com.api.mimosbyliv.dto.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UsuarioForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try{
            Authentication auth = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(auth);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        }
        catch(AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{token}")
    public String verificaToken(@PathVariable String token){
        if(tokenService.isTokenValido(token)){
            return "SUCESSO";
        }

        return "ERRO";
    }

}
