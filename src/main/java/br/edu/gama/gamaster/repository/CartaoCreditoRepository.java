package br.edu.gama.gamaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.gama.gamaster.model.CartaoCredito;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long>{
	
	@Query(nativeQuery = true, value = "select * from tb_cartao where cod_conta = ?1")
	Optional<CartaoCredito> findByConta(Long idConta);

}
