package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Transacao;
import br.edu.estudos.barriga.domain.builders.TransacaoBuilder;
import br.edu.estudos.barriga.repositories.TransacaoDao;
import br.edu.estudos.barriga.service.external.ClockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @Mock
    TransacaoDao dao;

    @Mock
    ClockService clockService;

    @InjectMocks
    @Spy
    TransacaoService transacaoService;

//    @BeforeEach
//    void checkTime(){
//        Assumptions.assumeTrue(LocalDateTime.now().getHour() < 7) ;
//    }

    @Test
    public void deveSalvarTransacaoValida() {
        Transacao transacaoParaSalvar = TransacaoBuilder.umTransacao().agora();
        Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(TransacaoBuilder.umTransacao().agora());
        Mockito.when(transacaoService.getTime()).thenReturn(LocalDateTime.of(2024, 2, 11, 9, 20, 0));

        Transacao transacaoSalva = transacaoService.salvar(transacaoParaSalvar);

        Assertions.assertEquals(TransacaoBuilder.umTransacao().agora(), transacaoSalva);
        Assertions.assertAll("Transação",
                () -> Assertions.assertEquals(1L, transacaoSalva.getId()),
                () -> Assertions.assertEquals("Transação Válida", transacaoSalva.getDescricao()),
                () -> Assertions.assertEquals(10.0, transacaoSalva.getValor()),
                () -> {
                    Assertions.assertAll("Conta",
                            () -> Assertions.assertEquals("Conta Válida", transacaoSalva.getConta().nome()),
                            () -> {
                                Assertions.assertAll("Usuario",
                                        () -> Assertions.assertEquals("usuario valido", transacaoSalva.getConta().usuario().getNome()),
                                        () -> Assertions.assertEquals("123456", transacaoSalva.getConta().usuario().getSenha())
                                );
                            }
                    );
                }
        );
    }


//    private static boolean isHoraValida(){
//        return LocalDateTime.now().getHour() < 8;
//    }
}