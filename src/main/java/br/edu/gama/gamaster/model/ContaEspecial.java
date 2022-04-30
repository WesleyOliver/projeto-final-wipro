package br.edu.gama.gamaster.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CE")
@Getter
@Setter
public class ContaEspecial extends Conta {

	private BigDecimal limiteDeCreditoPreAprovado;

}
