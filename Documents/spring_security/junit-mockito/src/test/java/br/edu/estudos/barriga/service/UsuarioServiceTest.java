package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Usuario;
import br.edu.estudos.barriga.domain.builders.UsuarioBuilder;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import br.edu.estudos.barriga.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;


//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente(){
        Mockito.when(usuarioRepository.getUserByEmail("email@gmail.com")).thenReturn(Optional.empty());

        Optional<Usuario> usuario = usuarioService.getUserByEmail("email@gmail.com");
        Assertions.assertTrue(usuario.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        Mockito.when(usuarioRepository.getUserByEmail("email@gmail.com")).thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        Optional<Usuario> usuario = usuarioService.getUserByEmail("email@gmail.com");
        Assertions.assertTrue(usuario.isPresent());

        Mockito.verify(usuarioRepository, Mockito.times(1)).getUserByEmail("email@gmail.com");
        Mockito.verify(usuarioRepository, Mockito.never()).getUserByEmail("emailoutro@gmail.com");
        Mockito.verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

        Mockito.when(usuarioRepository.getUserByEmail(userToSave.getEmail())).thenReturn(Optional.empty());
        Mockito.when(usuarioRepository.salvar(userToSave)).thenReturn(userToSave);

        Usuario savedUser = usuarioService.salvar(userToSave);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void deveRejeitarSalvarUsuarioExistente(){
        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

        Mockito.when(usuarioRepository.getUserByEmail(userToSave.getEmail())).thenReturn(Optional.of(userToSave));

        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->{
            usuarioService.salvar(userToSave);
        });

        Assertions.assertEquals("Usuario user@email.com já cadastrado!", ex.getMessage());
        Mockito.verify(usuarioRepository, Mockito.never()).salvar(userToSave);
    }
}