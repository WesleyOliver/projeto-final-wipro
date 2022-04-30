package br.edu.gama.gamaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.gama.gamaster.exceptionhandler.CartaoExistenteException;
import br.edu.gama.gamaster.model.CartaoCredito;
import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.repository.CartaoCreditoRepository;

@Service
public class CartaoCreditoService {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private ContaService contaService;

	public List<CartaoCredito> buscarTodos() {
		return cartaoCreditoRepository.findAll();
	}

	public CartaoCredito buscarPorCodigo(Long codigo) {
		CartaoCredito cartaoCredito = buscarCartaoPeloCodigo(codigo);
		return cartaoCredito;
	}

	private CartaoCredito buscarCartaoPeloCodigo(Long codigo) {
		Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(codigo);
		if (!cartaoCredito.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return cartaoCredito.get();
	}

	public CartaoCredito salvarCartao(Long codigo) {
		Optional<CartaoCredito> cartaoSalvo = cartaoCreditoRepository.findByConta(codigo);
		if(cartaoSalvo.isPresent()) {
			throw new CartaoExistenteException();
		}
		CriarCartaoCredito criarCartao = new CriarCartaoCredito();
		Conta conta = contaService.buscarPorCodigo(codigo);
		return cartaoCreditoRepository.save(criarCartao.gerarCartao(conta));

	}
}
