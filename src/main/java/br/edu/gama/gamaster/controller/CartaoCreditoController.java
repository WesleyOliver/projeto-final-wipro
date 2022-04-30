package br.edu.gama.gamaster.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.gama.gamaster.event.RecursoCriadoEvent;
import br.edu.gama.gamaster.model.CartaoCredito;
import br.edu.gama.gamaster.service.CartaoCreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cartao-credito")
public class CartaoCreditoController {

	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@Operation(summary = "Retorna todos os objetos cartões cadastrados", tags = {"Cartão-Credito"})
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Successful Operation")})
	public List<CartaoCredito> listar() {
		List<CartaoCredito> cartoes = cartaoCreditoService.buscarTodos();
		return cartoes;
	}
	
	@GetMapping("/{codigo}")
	@Operation(summary = "Retorna apenas um objeto cartão-credito referente ao codigo informado", tags = {"Cartão-Credito"})
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successful Operation"),
		@ApiResponse(responseCode = "404", description = "Object Cartão-Credito Not_Found")})
	public ResponseEntity<CartaoCredito> buscarPorCodigo(@PathVariable Long codigo) {
		CartaoCredito cartaoCredito = cartaoCreditoService.buscarPorCodigo(codigo);
		return ResponseEntity.ok(cartaoCredito);
	}
	
	@PostMapping
	@Operation(summary = "Cria e retorna um objeto cartão-credito informando o numero da conta.", tags = {"Cartão-Credito"})
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Created")})
	public ResponseEntity<CartaoCredito> cadastrar(@RequestParam(name = "codigoConta") Long codigoConta, HttpServletResponse response) {
		CartaoCredito cartaoSalvo = cartaoCreditoService.salvarCartao(codigoConta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cartaoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoSalvo);
	}
	
}
