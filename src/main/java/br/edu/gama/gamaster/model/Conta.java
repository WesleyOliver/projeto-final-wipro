package br.edu.gama.gamaster.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_conta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
public abstract class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta")
	private TipoConta tipoConta;

	@Column(name = "saldo")
	private BigDecimal saldo = BigDecimal.ZERO;

	@NotBlank
	@Column(name = "agencia")
	private String agencia;

	@NotBlank
	@Column(name = "numero_conta")
	private String numeroConta;

	@NotNull
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	@OneToMany(mappedBy = "contaOrigem")
	@JsonBackReference
	@ToString.Exclude
	private List<Movimentacao> movimentacoesOrigem = new ArrayList<>();

	@OneToMany(mappedBy = "contaDestino")
	@JsonBackReference
	@ToString.Exclude
	private List<Movimentacao> movimentacoesDestino = new ArrayList<>();

	@NotNull
	@OneToOne
	@JoinColumn(name = "cod_cliente", nullable = false)
	private Cliente cliente;
}
