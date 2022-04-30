package br.edu.gama.gamaster.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("CC")
@Getter
@Setter
@NoArgsConstructor
public class ContaCorrente extends Conta {

	public ContaCorrente(Long codigo, TipoConta tipoConta, BigDecimal saldo, String agencia, String numeroConta,
			LocalDate dataCriacao, List<Movimentacao> movimentacoesOrigem, List<Movimentacao> movimentacoesDestino,
			Cliente cliente) {
	}
}
