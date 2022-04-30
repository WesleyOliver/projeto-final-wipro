package br.edu.gama.gamaster.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Endereco {

	@Column(name = "logradouro")
	@Size(max = 50)
	@Schema(description = "Rua ou Avenida...", example = "Rua das magnolhas")
	private String logradouro;
	
	@Column(name = "numero")
	@Size(max = 10)
	@Schema(description = "Número referente ao endereço", example = "158")
	private String numero;
	
	@Column(name = "complemento")
	@Size(max = 50)
	@Schema(description = "Complemento do endereço", example = "Qd. 28 Lt. 56")
	private String complemento;
	
	@Column(name = "bairro")
	@Size(max = 50)
	@Schema(description = "bairro referente ao endereço", example = "Jardim América")
	private String bairro;
	
	@Column(name = "cep")
	@Size(max = 20)
	@Schema(description = "Cep referente ao endereço", example = "01311-200")
	private String cep;
	
	@Column(name = "cidade")
	@Size(max = 50)
	@Schema(description = "Cidade referente ao endereço", example = "São Bernado dos Campos")
	private String cidade;
	
	@Column(name = "estado")
	@Size(max = 2)
	@Schema(description = "Sigla do estado referente ao endereço", example = "SP")
	private String estado;
	
}
