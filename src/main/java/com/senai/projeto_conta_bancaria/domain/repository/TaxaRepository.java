package com.senai.projeto_conta_bancaria.domain.repository;

import com.senai.projeto_conta_bancaria.domain.entity.Pagamento;
import com.senai.projeto_conta_bancaria.domain.entity.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxaRepository extends JpaRepository<Taxa, String> {
    Optional<Taxa> findByTaxaTrue(String Pagamento);
}
