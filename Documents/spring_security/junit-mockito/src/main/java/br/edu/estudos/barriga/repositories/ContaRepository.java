package br.edu.estudos.barriga.repositories;

import br.edu.estudos.barriga.domain.Conta;

import java.util.List;

public interface ContaRepository {

    Conta salvar(Conta conta);

    List<Conta> obterContasPorUsuario(Long idUsuario);

    void deletar(Conta conta);
}
