package br.edu.gama.gamaster.repository;

import br.edu.gama.gamaster.model.ContaCorrente;

import javax.transaction.Transactional;

@Transactional
public interface ContaCorrenteRepository extends ContaBaseRepository<ContaCorrente> {
}
