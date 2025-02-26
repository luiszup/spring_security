package br.edu.estudos.barriga.repositories;

import br.edu.estudos.barriga.domain.Transacao;

public interface TransacaoDao {
    Transacao salvar(Transacao transacao);
}
