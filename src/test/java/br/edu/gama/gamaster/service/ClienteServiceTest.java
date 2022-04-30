package br.edu.gama.gamaster.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.model.Endereco;
import br.edu.gama.gamaster.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

	@InjectMocks
	private ClienteService clienteService;

	@Mock
	private ClienteRepository clienteRepository;
	

	@Test
	void testBuscarTodos() {
		Cliente cliente1 = new Cliente(1L, "Cliente Gamaster1", "125.856.965-58", "(62) 3698-6325", new Endereco());
		Cliente cliente2 = new Cliente(2L, "Cliente Gamaster2", "125.856.965-58", "(62) 3698-6325", new Endereco());
		Cliente cliente3 = new Cliente(3L, "Cliente Gamaster3", "125.856.965-58", "(62) 3698-6325", new Endereco());

		List<Cliente> list = new ArrayList<>();
		list.add(cliente1);
		list.add(cliente2);
		list.add(cliente3);

		when(clienteRepository.findAll()).thenReturn(list);

		List<Cliente> clientes = clienteService.buscarTodos();
		assertEquals(3, clientes.size());
	}

	@Test
	void testBuscarPorCodigo() {
		when(clienteRepository.findById(1L)).thenReturn(
				Optional.of(new Cliente(1L, "Cliente Gamaster", "125.856.965-58", "(62) 3698-6325", new Endereco())));

		Cliente cliente = clienteService.buscarPorCodigo(1L);

		assertEquals("Cliente Gamaster", cliente.getNome());
	}

	@Test
	void testAtualizarCliente() {
		Cliente clienteAtualizado = new Cliente(1L, "Cliente Gamaster2", "125.856.965-58", "(62) 3698-6325",
				new Endereco());
		Cliente clienteEncontrado = new Cliente(1L, "Cliente Gamaster", "125.856.965-58", "(62) 3698-6325",
				new Endereco());

		when(clienteRepository.save(clienteAtualizado)).thenReturn(clienteAtualizado);
		when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEncontrado));

		Cliente cliente = clienteService.atualizarCliente(1L, clienteAtualizado);

		assertEquals("Cliente Gamaster2", cliente.getNome());
		assertEquals(clienteEncontrado.getCodigo(), cliente.getCodigo());
	}

	@Test
	void testSalvarCliente() {
		Cliente cliente = new Cliente(1L, "Cliente Gamaster", "125.856.965-58", "(62) 3698-6325", new Endereco());

		clienteService.salvarCliente(cliente);

		verify(clienteRepository, times(1)).save(cliente);
	}

	@Test
	void testBuscarPorCodigo_ClinteNaoEncontradoException() {
		when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(EmptyResultDataAccessException.class, () -> {
			clienteService.buscarPorCodigo(1L);
		});
	}
}
