package br.edu.gama.gamaster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_cartao")
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@AllArgsConstructor
@NoArgsConstructor
public class CartaoCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Codigo gerado pela API", example = "1")
	private Long codigo;
	
	@NotBlank
	@Column(name = "numero")
	@Schema(description = "Número do cartão de credito", example = "1234 4567 8912 3456")
    private String numero;
	
	@NotNull
	@Column(name = "validade")
	@Schema(description = "Data de validade do cartão", example = "2025-12-31")
    private LocalDate validade;
	
	@NotBlank
	@Column(name = "cod_seguranca")
	@Schema(description = "Codigo de segurança do cartão", example = "198")
    private String codigoSeguranca;
	
	@NotBlank
	@Column(name = "bandeira")
	@Schema(description = "Bandeira do cartão", example = "Mastercard")
    private String bandeira;
	
	@NotBlank
	@Column(name = "portador")
	@Schema(description = "Nome do portador do cartão", example = "Jose da Silva Gonçalves")
    private String nomePortador;
	
	@NotNull
	@Column(name = "limite")
	@Schema(description = "Valor do limite do cartão", example = "4000.00")
	private BigDecimal limite;
	
	@Column(name = "limite_disponivel")
	@Schema(description = "Limite disponivél no cartão gerenciado pela API", example = "2500.00")
	private BigDecimal limiteDesponivel;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "cod_conta")
	@ToString.Exclude
	private Conta conta;

}
