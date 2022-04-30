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
public class MovimentacaoTransferenciaDto {

    @NotNull
	@Schema(description = "Valor da movimentação", example = "500.00")
    private BigDecimal valor;

    @NotNull
	@Schema(description = "Conta de origem da movimentação", example = "1")
    private Long codigoContaOrigem;

    @NotNull
	@Schema(description = "Conta de destino da movimentação", example = "2")
    private Long codigoContaDestino;

    public Movimentacao toModel(ContaService contaService){
        Movimentacao movimentacao = new Movimentacao();
        Conta contaOrigem = contaService.buscarPorCodigo(this.codigoContaOrigem);
        Conta contaDestino = contaService.buscarPorCodigo(this.codigoContaDestino);
        movimentacao.setValor(this.valor);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setContaOrigem(contaOrigem);
        movimentacao.setContaDestino(contaDestino);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.TRANSFERENCIA);

//        if (this.codigoContaOrigem == null && this.codigoContaDestino == null) {
//            throw new ContasInformadasInvalidasException();
//        }
        return movimentacao;
    }
}
