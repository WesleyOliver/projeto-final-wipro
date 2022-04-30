package br.edu.gama.gamaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.gama.gamaster.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
