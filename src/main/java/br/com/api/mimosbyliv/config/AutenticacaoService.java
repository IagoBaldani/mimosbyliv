package br.com.api.mimosbyliv.config;

import br.com.api.mimosbyliv.model.Usuario;
import br.com.api.mimosbyliv.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = usuarioRepository.findByNome(nome);

        if(optUsuario.isPresent()){
            return optUsuario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
