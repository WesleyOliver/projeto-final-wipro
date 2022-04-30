package br.edu.gama.gamaster.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.gama.gamaster.model.CartaoCredito;
import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.model.ContaCorrente;
import br.edu.gama.gamaster.model.ContaEspecial;
import br.edu.gama.gamaster.model.Endereco;
import br.edu.gama.gamaster.model.dto.ContaDto;
import br.edu.gama.gamaster.repository.CartaoCreditoRepository;
import br.edu.gama.gamaster.repository.ContaRepository;

@ExtendWith(MockitoExtension.class)
class CartaoCreditoServiceTest {

	@InjectMocks
	private CartaoCreditoService cartaoCreditoService;

	@Mock
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Test
	void testBuscarTodos() {
		CartaoCredito cartao1 = new CartaoCredito(1L, "1478963214789632", LocalDate.now(), "125", "MasterCard",
				"Jóse Silva", new BigDecimal(4000), new BigDecimal(1500), new ContaCorrente());
		CartaoCredito cartao2 = new CartaoCredito(1L, "1452369814523698", LocalDate.now(), "125", "MasterCard",
				"Maria Barbosa", new BigDecimal(8000), new BigDecimal(1500), new ContaEspecial());
		CartaoCredito cartao3 = new CartaoCredito(1L, "52896325252896325", LocalDate.now(), "125", "MasterCard",
				"Danilo Santos", new BigDecimal(4000), new BigDecimal(1500), new ContaCorrente());

		List<CartaoCredito> list = new ArrayList<>();
		list.add(cartao1);
		list.add(cartao2);
		list.add(cartao3);

		when(cartaoCreditoRepository.findAll()).thenReturn(list);
		List<CartaoCredito> cartoes = cartaoCreditoService.buscarTodos();

		assertEquals(3, cartoes.size());
	}

	@Test
	void testBuscarPorCodigo() {
		when(cartaoCreditoRepository.findById(1L))
				.thenReturn(Optional.of(new CartaoCredito(1L, "1478963214789632", LocalDate.now(), "125", "MasterCard",
						"Jóse Silva", new BigDecimal(4000), new BigDecimal(1500), new ContaCorrente())));
		
		CartaoCredito cartao = cartaoCreditoService.buscarPorCodigo(1L);
		assertEquals("1478963214789632", cartao.getNumero());
	}
}
