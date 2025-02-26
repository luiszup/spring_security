package br.edu.estudos.barriga.service.external;

import br.edu.estudos.barriga.domain.Conta;

public interface ContaEvent {
    public enum EventType{
        CREATED,
        UPDATED,
        DELETED
    }

    void dispatch(Conta conta, EventType eventType) throws Exception;
}
