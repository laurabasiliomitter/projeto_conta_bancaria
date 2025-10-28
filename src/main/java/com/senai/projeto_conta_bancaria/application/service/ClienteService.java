package com.senai.projeto_conta_bancaria.application.service;

import com.senai.projeto_conta_bancaria.application.dto.ClienteRegistroDTO;
import com.senai.projeto_conta_bancaria.application.dto.ClienteResponseDTO;
import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import com.senai.projeto_conta_bancaria.domain.exception.ContaDoMesmoTipoException;
import com.senai.projeto_conta_bancaria.domain.exception.EntidadeNaoEncontradaException;
import com.senai.projeto_conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    private final PasswordEncoder encoder;

    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> repository.save(dto.toEntity())
        );
        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean jaTemTipo = contas.stream().anyMatch(
                c -> c.getClass().equals(novaConta.getClass()) && c.isAtiva()
        );
        if (jaTemTipo)
            throw new ContaDoMesmoTipoException();

        cliente.setSenha(encoder.encode(dto.senha()));
        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public List<ClienteResponseDTO> listarClientesAtivos() {
        return repository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    public ClienteResponseDTO buscarClienteAtivoporCpf(String cpf) {
        var cliente = buscarClientePorCpfEAtivo(cpf);
        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    private Cliente buscarClientePorCpfEAtivo(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new EntidadeNaoEncontradaException("cliente")
        );
        return cliente;
    }

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteRegistroDTO dto) {
        var cliente = buscarClientePorCpfEAtivo(cpf);

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(cliente);
    }

    public void deletarCliente(String cpf){

        var cliente = buscarClientePorCpfEAtivo(cpf);
        cliente.setAtivo(false);
        cliente.getContas().forEach(
                conta -> conta.setAtiva(false)
        );
        repository.save(cliente);
    }
}
