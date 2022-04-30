package br.edu.gama.gamaster.model.dto;

import br.edu.gama.gamaster.model.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {

    @NotBlank
    @Schema(description = "Refêrencia a instituição financeira", example = "0598")
    private String agencia;

    @NotBlank
    @Schema(description = "Número para identificação da conta o usuário", example = "1589632-8")
    private String numeroConta;

    @NotNull
    @Schema(description = "Código de identificação do cliente", example = "1")
    private Cliente cliente;

}
