package br.edu.gama.gamaster.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.gama.gamaster.event.RecursoCriadoEvent;
import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@Operation(summary = "Retorna todos os objetos clientes cadastradas", tags = {"Cliente"})
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Successful Operation")})
	public List<Cliente> listar() {
		List<Cliente> clientes = clienteService.buscarTodos();
		return clientes;
	}

	@GetMapping("/{codigo}")
	@Operation(summary = "Retorna apenas um objeto cliente referente ao codigo informado", tags = {"Cliente"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successful Operation"),
		@ApiResponse(responseCode = "404", description = "Object Cliente Not_Found")})	
	public ResponseEntity<Cliente> buscarPorCodigo(@PathVariable Long codigo) {
		Cliente cliente = clienteService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	@Operation(summary = "Cria e retorna um objeto cliente", tags = {"Cliente"})
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Created")})
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalvo = clienteService.salvarCliente(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@PutMapping("/{codigo}")
	@Operation(summary = "Atualiza apenas um objeto cliente referente ao codigo informado", tags = {"Cliente"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successful Operation"),
		@ApiResponse(responseCode = "404", description = "Object Cliente Not_Found")})
	public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.atualizarCliente(codigo, cliente);
		return ResponseEntity.ok(clienteAtualizado);
	}

}
