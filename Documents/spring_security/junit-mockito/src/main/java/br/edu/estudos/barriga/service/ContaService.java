package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Conta;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import br.edu.estudos.barriga.repositories.ContaRepository;
import br.edu.estudos.barriga.service.external.ContaEvent;

import java.time.LocalDateTime;
import java.util.List;

public class ContaService {

    private ContaRepository contaRepository;
    private ContaEvent event;

    public ContaService(ContaRepository contaRepository, ContaEvent event) {
        this.contaRepository = contaRepository;
        this.event = event;
    }

    public Conta salvar(Conta conta){
        List<Conta> contas = contaRepository.obterContasPorUsuario(conta.id());
        contas.stream().forEach(c -> {
            if(conta.nome().equalsIgnoreCase(c.nome())){
                throw new ValidationException("Usuario já possui uma conta com este nome");
            }
        });
        Conta contaPersistida = contaRepository.salvar(new Conta(conta.id(), conta.nome() + LocalDateTime.now(), conta.usuario()));
        try {
            event.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        }catch (Exception ex){
            contaRepository.deletar(contaPersistida);
            throw new RuntimeException("Falha na criação da conta, tente novamente");
        }
        return contaPersistida;
    }

}
