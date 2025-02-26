package br.edu.estudos.barriga.repositories;

import br.edu.estudos.barriga.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUserByEmail(String email);

}
