package br.edu.gama.gamaster.service;

import java.util.List;

import br.edu.gama.gamaster.model.dto.MovimentacaoDepositoDto;
import br.edu.gama.gamaster.model.dto.MovimentacaoSaqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.Movimentacao;
import br.edu.gama.gamaster.model.dto.MovimentacaoTransferenciaDto;
import br.edu.gama.gamaster.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ContaService contaService;

    public List<Movimentacao> buscarMovimentacaoPorConta(Long codigoConta) {
        Conta conta = contaService.buscarPorCodigo(codigoConta);

        return movimentacaoRepository.findByContaOrigemOrContaDestino(conta, conta);
    }

    public List<Movimentacao> buscarMovimentacaoEntreContas(Long codigoContaOrigem, Long codigoContaDestino) {
        Conta contaOrigem = contaService.buscarPorCodigo(codigoContaOrigem);
        Conta contaDestino = contaService.buscarPorCodigo(codigoContaDestino);

        return movimentacaoRepository.findByContaOrigemAndContaDestino(contaOrigem, contaDestino);
    }

    public List<Movimentacao> buscarSaidasPorConta(Long codigoConta) {
        return movimentacaoRepository.buscarSaidasPorConta(codigoConta);
    }

    public List<Movimentacao> buscarEntradasPorConta(Long codigoConta) {
        return movimentacaoRepository.buscarEntradasPorConta(codigoConta);
    }

    public Movimentacao fazerTransferencia(MovimentacaoTransferenciaDto movimentacaoTransferenciaDto) {
        Movimentacao movimentacao = movimentacaoTransferenciaDto.toModel(contaService);
        contaService.atualizarSaldo(movimentacao.getContaOrigem(), movimentacao.getContaDestino(), movimentacao.getValor());
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao fazerDeposito(MovimentacaoDepositoDto movimentacaoDepositoDto) {
        Movimentacao movimentacao = movimentacaoDepositoDto.toModel(contaService);
        contaService.atualizarSaldo(movimentacao.getContaOrigem(), movimentacao.getContaDestino(), movimentacao.getValor());
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao fazerSaque(MovimentacaoSaqueDto movimentacaoSaqueDto) {
        Movimentacao movimentacao = movimentacaoSaqueDto.toModel(contaService);
        contaService.atualizarSaldo(movimentacao.getContaOrigem(), movimentacao.getContaDestino(), movimentacao.getValor());
        return movimentacaoRepository.save(movimentacao);
    }
}
