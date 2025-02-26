package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Conta;
import br.edu.estudos.barriga.domain.builders.ContaBuilder;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import br.edu.estudos.barriga.repositories.ContaRepository;
import br.edu.estudos.barriga.service.external.ContaEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    ContaRepository contaRepository;

    @Mock
    private ContaEvent event;

    @InjectMocks
    ContaService contaService;

    @Captor
    private ArgumentCaptor<Conta> contaCaptor;

    @Test
    public void deveSalvarPrimeiraContaComSucesso() throws Exception{
        Conta accountToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(ContaBuilder.umaConta().agora());
        Mockito.doNothing().when(event).dispatch(ContaBuilder.umaConta().agora(), ContaEvent.EventType.CREATED);

        Conta savedAccount = contaService.salvar(accountToSave);
        Assertions.assertNotNull(savedAccount.id());

        Mockito.verify(contaRepository).salvar(contaCaptor.capture());
        Assertions.assertTrue(contaCaptor.getValue().nome().startsWith("Conta Válida"));
    }

    @Test
    public void deveSalvarSegundaConta(){
        Conta accountToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(contaRepository.obterContasPorUsuario(accountToSave.id())).thenReturn(Arrays.asList(ContaBuilder.umaConta().comNome("OutraConta").agora()));
        Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(ContaBuilder.umaConta().agora());

        Conta savedAccount = contaService.salvar(accountToSave);

        Assertions.assertNotNull(savedAccount.id());
    }

    @Test
    public void deveRejeitarContaRepetida(){
        Conta accountToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(contaRepository.obterContasPorUsuario(accountToSave.id())).thenReturn(Arrays.asList(ContaBuilder.umaConta().agora()));

        String message = Assertions.assertThrows(ValidationException.class, ()-> contaService.salvar(accountToSave)).getMessage();
        Assertions.assertEquals("Usuario já possui uma conta com este nome", message);
    }

    @Test
    public void naoDeveManterContaSemEvento() throws Exception{
        Conta accountToSave = ContaBuilder.umaConta().comId(null).agora();
        Conta contaSalva = ContaBuilder.umaConta().agora();

        Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(ContaBuilder.umaConta().agora());
        Mockito.doThrow(new Exception("error")).when(event).dispatch(contaSalva, ContaEvent.EventType.CREATED);

        String message = Assertions.assertThrows(Exception.class, ()-> contaService.salvar(accountToSave)).getMessage();
        Assertions.assertEquals("Falha na criação da conta, tente novamente", message);

        Mockito.verify(contaRepository).deletar(contaSalva);
    }
}