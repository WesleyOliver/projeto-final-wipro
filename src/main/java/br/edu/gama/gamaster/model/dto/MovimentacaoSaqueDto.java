package br.edu.gama.gamaster.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.Movimentacao;
import br.edu.gama.gamaster.model.TipoMovimentacao;
import br.edu.gama.gamaster.service.ContaService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoSaqueDto {

    @NotNull
	@Schema(description = "Valor do saque", example = "500.00")
    private BigDecimal valor;

    @NotNull
	@Schema(description = "Conta de realização do saque", example = "1")
    private Long codigoConta;

    public Movimentacao toModel(ContaService contaService){
        Movimentacao movimentacao = new Movimentacao();
        Conta contaOrigem = contaService.buscarPorCodigo(this.codigoConta);
        movimentacao.setValor(this.valor);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setContaOrigem(contaOrigem);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);

        return movimentacao;
    }
}
