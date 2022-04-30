package br.edu.gama.gamaster.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import br.edu.gama.gamaster.model.CartaoCredito;
import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.TipoConta;

public class CriarCartaoCredito {
	
	private Integer interador;	
	Random random = new Random();

	protected CartaoCredito gerarCartao(Conta conta) {
		CartaoCredito cartaoCredito = new CartaoCredito();
		cartaoCredito.setConta(conta);
		cartaoCredito.setNumero(this.gerarNumeroCartao());
		cartaoCredito.setCodigoSeguranca(this.gerarCodigoSeguranca());
		cartaoCredito.setBandeira("MasterCard");
		cartaoCredito.setNomePortador(conta.getCliente().getNome());
		cartaoCredito.setValidade(LocalDate.now().plusYears(5));
		if (conta.getTipoConta().equals(TipoConta.CONTA_CORRENTE)) {
			cartaoCredito.setLimite(new BigDecimal(4000.00));
		} else {
			cartaoCredito.setLimite(new BigDecimal(10000.00));
		}

		return cartaoCredito;
	}
	
	
	private String gerarNumeroCartao() {		
		String numeroCartao = "";
		interador = 0;
		while(interador < 16) {
			numeroCartao += String.valueOf(random.nextInt(9)) ;
			interador++;
		}
		return numeroCartao;
	}
	
	private String gerarCodigoSeguranca() {
		String codigoSegurancao = "";
		interador = 0;
		while(interador < 3) {
			codigoSegurancao += String.valueOf(random.nextInt(9)) ;
			interador++;
		}
		return codigoSegurancao;
	}

}
