package br.edu.gama.gamaster.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import br.edu.gama.gamaster.model.ContaEspecial;
import br.edu.gama.gamaster.model.TipoConta;
import br.edu.gama.gamaster.model.dto.ContaDto;

public class CriarContaEspecial {

	protected static ContaEspecial criarContaEspecial(ContaDto contaDto) {
		ContaEspecial contaEspecial = new ContaEspecial();
		BeanUtils.copyProperties(contaDto, contaEspecial, "codigo");
		contaEspecial.setLimiteDeCreditoPreAprovado(new BigDecimal(300.00));
		contaEspecial.setTipoConta(TipoConta.CONTA_ESPECIAL);
		contaEspecial.setDataCriacao(LocalDate.now());
		return contaEspecial;
	}

}
