package br.edu.estudos.barriga.domain;

import br.edu.estudos.barriga.domain.builders.ContaBuilder;
import br.edu.estudos.barriga.domain.builders.UsuarioBuilder;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Teste de Domínio Conta")
public class ContaTest {

    @Test
    @DisplayName("Deve criar uma conta válida")
    void deveCriarContaValida(){
        Conta conta = ContaBuilder.umaConta().agora();

        Assertions.assertAll("Conta",
                () -> Assertions.assertEquals(1L, conta.id()),
                () -> Assertions.assertEquals("Conta Válida", conta.nome()),
                () -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.usuario())
         );
    }

    @ParameterizedTest(name = "[{index}] - {3}")
    @MethodSource(value = "dataProvider")
    @DisplayName("Deve validar os compos obrigatórios")
    void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem){

        String ex = Assertions.assertThrows(ValidationException.class, ()->{
            ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora();
        }).getMessage();

        Assertions.assertEquals(mensagem, ex);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "Nome é obrigatório"),
                Arguments.of(1L, "Conta Valida", null, "Usuário é obrigatório")
        );
    }


}
