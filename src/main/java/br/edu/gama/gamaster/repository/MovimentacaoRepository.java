package br.edu.gama.gamaster.repository;

import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

//    @Query(nativeQuery = true, value = "select * from tb_movimentacao where cod_conta_destino = ?1 or cod_conta_origem = ?2")
    List<Movimentacao> findByContaOrigemOrContaDestino(Conta contaOrigem, Conta contaDestino);
    List<Movimentacao> findByContaOrigemAndContaDestino(Conta contaOrigem, Conta contaDestino);

    @Query(nativeQuery = true, value = "select * from tb_movimentacao where cod_conta_origem = ?1")
    List<Movimentacao> buscarSaidasPorConta(Long codigoConta);

    @Query(nativeQuery = true, value = "select * from tb_movimentacao where cod_conta_destino = ?1")
    List<Movimentacao> buscarEntradasPorConta(Long codigoConta);

}
