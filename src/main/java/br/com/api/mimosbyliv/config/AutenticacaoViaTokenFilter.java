package br.com.api.mimosbyliv.config;


import br.com.api.mimosbyliv.model.Usuario;
import br.com.api.mimosbyliv.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        Boolean valido = tokenService.isTokenValido(token);

        if(valido){
            System.out.println("Token válido");
            autenticarCliente(token);
        }
        else{
            System.out.println("Token inválido");
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Integer idUsuario = tokenService.getIdUsuario(token);
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);

        if(optUsuario.isPresent()){
            Usuario usuario = optUsuario.get();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
