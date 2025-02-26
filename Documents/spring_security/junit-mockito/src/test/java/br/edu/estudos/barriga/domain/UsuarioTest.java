package br.edu.estudos.barriga.domain;

import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static br.edu.estudos.barriga.domain.builders.UsuarioBuilderOld.umUsuario;

@DisplayName("Teste de Domínio Usuário")
class UsuarioTest {
    @Test
    @DisplayName("Deve criar um usuário válido")
    void deveCriarUsuarioValido(){
        Usuario usuario = umUsuario().agora();
//        Faz os teste e pára quando encontra um erro
//        Assertions.assertEquals(1L, usuario.getId());
//        Assertions.assertEquals("usuario valido", usuario.getNome());
//        Assertions.assertEquals("user@email.com", usuario.getEmail());
//        Assertions.assertEquals("123456", usuario.getSenha());

//      Faz o teste e mostra todos os erros ao final
        Assertions.assertAll("Usuário",
                () -> Assertions.assertEquals(1L, usuario.getId()),
                () -> Assertions.assertEquals("usuario valido", usuario.getNome()),
                () -> Assertions.assertEquals("user@email.com", usuario.getEmail()),
                () -> Assertions.assertEquals("123456", usuario.getSenha())
        );
    }

    @Test
    @DisplayName("Deve retornar um erro ao tentar criar um usuário sem nome")
    void deveRejeitarUsuarioSemNome(){
        // "captando" o erro
        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->
                umUsuario().comNome(null).agora()
        );
        // verificando se o erro é o certo
        Assertions.assertEquals("Nome é obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um erro ao tentar criar um usuário sem email")
    void deveRejeitarUsuarioSemEmail(){

        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->
                umUsuario().comEmail(null).agora()
        );

        Assertions.assertEquals("Email é obrigatório", ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um erro ao tentar criar um usuário sem email")
    void deveRejeitarUsuarioSemSenha(){

        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->
                umUsuario().comSenha(null).agora()
        );

        Assertions.assertEquals("Senha é obrigatório", ex.getMessage());
    }
    // tem a mesma função que os 3 três anteriores
    @DisplayName("Deve validar os compos obrigatórios")
    @ParameterizedTest(name = "[{index}] - {4}")
    @CsvSource(value = {
            "1, NULL, user@mail.com, 123456, Nome é obrigatório",
            "1, usuario, NULL, 123456, Email é obrigatório",
            "1, usuario, user@mail.com,NULL, Senha é obrigatório"
    }, nullValues = "NULL")
    void deveValidarCamposObrigatorio(Long id, String nome, String email, String senha, String mensagem){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, ()->
                umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora()
        );

        Assertions.assertEquals(mensagem, ex.getMessage());
    }

}