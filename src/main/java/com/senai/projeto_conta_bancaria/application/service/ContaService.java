package com.senai.projeto_conta_bancaria.application.service;

import com.senai.projeto_conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.senai.projeto_conta_bancaria.application.dto.ContaResumoDTO;
import com.senai.projeto_conta_bancaria.application.dto.TransferenciaDTO;
import com.senai.projeto_conta_bancaria.application.dto.ValorSaqueDepositoDTO;
import com.senai.projeto_conta_bancaria.domain.entity.Conta;
import com.senai.projeto_conta_bancaria.domain.entity.ContaCorrente;
import com.senai.projeto_conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.projeto_conta_bancaria.domain.exception.EntidadeNaoEncontradaException;
import com.senai.projeto_conta_bancaria.domain.exception.RendimentoInvalidoException;
import com.senai.projeto_conta_bancaria.domain.exception.TipoDeContaInvalidaException;
import com.senai.projeto_conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContaService {
    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasContas() {
        return repository.findAllByAtivaTrue().stream()
                .filter(Conta::isAtiva)
                .map(ContaResumoDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarContaPorNumero(String numero) {
        return ContaResumoDTO.fromEntity(
                buscarContaAtivaPorNumero(numero)
        );
    }

    public ContaResumoDTO atualizarConta(String numeroDaConta, ContaAtualizadaDTO dto) {
        Conta conta = buscarContaAtivaPorNumero(numeroDaConta);

        if (conta instanceof ContaPoupanca poupanca) {
            poupanca.setRendimento(dto.rendimento());
        } else if (conta instanceof ContaCorrente corrente) {
            ((ContaCorrente) conta).setLimite(dto.limite());
            ((ContaCorrente) conta).setTaxa(dto.taxa());

        }else {

            throw new TipoDeContaInvalidaException("");
        }
        conta.setSaldo(dto.saldo());

        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public void deletarConta(String numeroDaConta) {
        Conta conta = buscarContaAtivaPorNumero(numeroDaConta);
        conta.setAtiva(false);
        repository.save(conta);
    }

    public ContaResumoDTO sacar(String numeroDaConta, ValorSaqueDepositoDTO dto) {
        Conta conta = buscarContaAtivaPorNumero(numeroDaConta);

       conta.sacar(dto.valor());
       return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public ContaResumoDTO depositar(String numeroDaConta, ValorSaqueDepositoDTO dto) {
        Conta conta = buscarContaAtivaPorNumero(numeroDaConta);

        conta.depositar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    private Conta buscarContaAtivaPorNumero(String numero){
        return repository.findByNumeroAndAtivaTrue(numero)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("conta"));
    }

    public ContaResumoDTO transferir(String numeroDaConta, TransferenciaDTO dto) {
        Conta contaOrigem = buscarContaAtivaPorNumero(numeroDaConta);
        Conta contaDestino = buscarContaAtivaPorNumero(dto.contaDestino());

        contaOrigem.transferir(dto.valor(), contaDestino);

        repository.save(contaDestino);
        return ContaResumoDTO.fromEntity(repository.save(contaOrigem));
    }

    public ContaResumoDTO aplicarRendimento(String numeroDaConta) {
        Conta conta = buscarContaAtivaPorNumero(numeroDaConta);
        if (conta instanceof ContaPoupanca poupanca) {
            poupanca.aplicarRendimento();
            return ContaResumoDTO.fromEntity(repository.save(poupanca));
        }
            throw new RendimentoInvalidoException();
        }

}
