package br.edu.gama.gamaster.repository;

import br.edu.gama.gamaster.model.ContaEspecial;

import javax.transaction.Transactional;

@Transactional
public interface ContaEspecialRepository extends ContaBaseRepository<ContaEspecial> {
}
