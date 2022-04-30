package br.edu.gama.gamaster.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import br.edu.gama.gamaster.model.ContaCorrente;
import br.edu.gama.gamaster.model.TipoConta;
import br.edu.gama.gamaster.model.dto.ContaDto;

public class CriarContaCorrente {
	
	protected static ContaCorrente criarContaCorrente(ContaDto contaDto) {
		ContaCorrente contaCorrente = new ContaCorrente();
		BeanUtils.copyProperties(contaDto, contaCorrente, "codigo");
		contaCorrente.setTipoConta(TipoConta.CONTA_CORRENTE);
		contaCorrente.setDataCriacao(LocalDate.now());
		return contaCorrente;
	}

}
