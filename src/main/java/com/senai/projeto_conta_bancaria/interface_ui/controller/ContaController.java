package com.senai.projeto_conta_bancaria.interface_ui.controller;

import com.senai.projeto_conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.senai.projeto_conta_bancaria.application.dto.ContaResumoDTO;
import com.senai.projeto_conta_bancaria.application.dto.TransferenciaDTO;
import com.senai.projeto_conta_bancaria.application.dto.ValorSaqueDepositoDTO;
import com.senai.projeto_conta_bancaria.application.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService service;

    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarTodasContas() {
        return ResponseEntity.ok(service.listarTodasContas());
    }

    @GetMapping("/{numeroDaConta}")
    public ResponseEntity<ContaResumoDTO> buscarContaPorNumero(@PathVariable @Valid String numeroDaConta) {
        return ResponseEntity.ok(service.buscarContaPorNumero(numeroDaConta));
    }

    @PostMapping("/{numeroDaConta}")
    public ResponseEntity <ContaResumoDTO> atualizarConta(@PathVariable String numeroDaConta,
                                                      @Valid  @RequestBody ContaAtualizadaDTO dto) {

        return ResponseEntity.ok(service.atualizarConta(numeroDaConta, dto));
    }

    @DeleteMapping("/{numeroDaConta}")
    public ResponseEntity<Void> deletarConta(@PathVariable @Valid String numeroDaConta) {
        service.deletarConta(numeroDaConta);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{numeroDaConta}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar( @PathVariable String numeroDaConta,
                                                @Valid  @RequestBody ValorSaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.sacar(numeroDaConta, dto));
    }

    @PostMapping("/{numeroDaConta}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar( @PathVariable String numeroDaConta,
                                                  @Valid @RequestBody ValorSaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.depositar(numeroDaConta, dto));
    }

    @PostMapping("/{numeroDaConta}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numeroDaConta,
                                                 @Valid  @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(numeroDaConta, dto));
    }

    @PostMapping("/{numeroDaConta}/rendimento")
        public ResponseEntity<ContaResumoDTO> aplicarRendimento(@PathVariable @Valid String numeroDaConta){
            return ResponseEntity.ok(service.aplicarRendimento(numeroDaConta));
        }

}
