package br.edu.gama.gamaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Cliente buscarPorCodigo(Long codigo) {
		Cliente cliente = buscarClientePeloCodigo(codigo);
		return cliente;
	}

	public Cliente atualizarCliente(Long codigo, Cliente cliente) {
		Cliente clienteSalvo = buscarClientePeloCodigo(codigo);
		BeanUtils.copyProperties(cliente, clienteSalvo, "codigo");
		return clienteRepository.save(clienteSalvo);
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	private Cliente buscarClientePeloCodigo(Long codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		if (!cliente.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return cliente.get();
	}

}
