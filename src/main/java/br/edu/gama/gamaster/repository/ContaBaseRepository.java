package br.edu.gama.gamaster.repository;

import br.edu.gama.gamaster.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContaBaseRepository<T extends Conta> extends JpaRepository<T, Long> {
}
