package br.edu.gama.gamaster.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoCreditoDto {
	
	@NotBlank
	@Schema(description = "Número do cartão de credito", example = "1234 4567 8912 3456")
    private String numero;
	
	@NotNull
	@Schema(description = "Data de validade do cartão", example = "2025-12-31")
    private LocalDate validade;
	
	@NotBlank
	@Schema(description = "Codigo de segurança do cartão", example = "198")
    private String codigoSeguranca;
	
	@NotBlank
	@Schema(description = "Bandeira do cartão", example = "Mastercard")
    private String bandeira;
	
	@NotBlank
	@Schema(description = "Nome do portador do cartão", example = "Jose da Silva Gonçalves")
    private String nomePortador;
	
	@NotNull
	@Schema(description = "Valor do limite do cartão", example = "4000.00")
	private BigDecimal limite;
	
	@NotNull
	@Schema(description = "Conta de vinculo do cartão", example = "1")
	private Long conta;

}
