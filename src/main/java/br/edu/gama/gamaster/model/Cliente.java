package br.edu.gama.gamaster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@Schema(description = "Codigo gerado pela API", example = "1")
	private Long codigo;
	
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "nome")
	@Schema(description = "Nome do cliente", example = "Jóse da Silva Gonçalves")
	private String nome;
	
	@NotBlank
	@Column(name = "cpf_cnpj")
	@Size(max = 25)
	@Schema(description = "Cpf ou Cnpj do cliente", example = "764.084.060-09")
	private String cpfCnpj;
	
	@NotBlank
	@Column(name = "telefone")
	@Size(max = 20)
	@Schema(description = "Telefone do Cliente", example = "(11) 3698-5214")
	private String telefone;
	
	@Embedded
	@Schema(description = "Endereço do cliente")
	private Endereco endereco;

}
