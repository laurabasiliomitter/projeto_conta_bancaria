package com.senai.projeto_conta_bancaria.domain.exception;

public class TransferirParaMesmaContaExcepition extends RuntimeException {

    public TransferirParaMesmaContaExcepition() {

        super("Não é possível transferir para a mesma conta");
    }
}
