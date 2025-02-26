package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Usuario;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import br.edu.estudos.barriga.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){
        usuarioRepository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new ValidationException(String.format("Usuario %s já cadastrado!", usuario.getEmail()));
        });
        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email){
        return usuarioRepository.getUserByEmail(email);
    }

}
