package br.edu.estudos.barriga.service;

import br.edu.estudos.barriga.domain.Transacao;
import br.edu.estudos.barriga.domain.exceptions.ValidationException;
import br.edu.estudos.barriga.repositories.TransacaoDao;
import br.edu.estudos.barriga.service.external.ClockService;

import java.time.LocalDateTime;

public class TransacaoService {
    private TransacaoDao dao;
    public Transacao salvar(Transacao transacao){
        if (getTime().getHour() > 9) throw new RuntimeException("Tente novamente amanhã");
        if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if (transacao.getData() == null) throw new ValidationException("Data inexistente");
        if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        if (transacao.getStatus() == null) transacao.setStatus(false);

        return dao.salvar(transacao);
    }

    protected LocalDateTime getTime(){
        return LocalDateTime.now();
    }

}
