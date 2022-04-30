package br.edu.gama.gamaster.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.gama.gamaster.exceptionhandler.ContaSemSaldoException;
import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.ContaCorrente;
import br.edu.gama.gamaster.model.ContaEspecial;
import br.edu.gama.gamaster.model.dto.ContaDto;
import br.edu.gama.gamaster.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> buscarTodasContas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorCodigo(Long codigo) {
        Conta conta = buscarContaPeloCodigo(codigo);
        return conta;
    }

    public Conta criarConta(Boolean isContaEspecial, ContaDto conta) {
        if (isContaEspecial) {
            return contaRepository.save(CriarContaEspecial.criarContaEspecial(conta));
        } else {
            return contaRepository.save(CriarContaCorrente.criarContaCorrente(conta));
        }
    }

    private Conta buscarContaPeloCodigo(Long codigo) {
        Optional<Conta> conta = contaRepository.findById(codigo);
        if (conta.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return conta.get();
    }

    @Transactional //Incluído para caso haja algum problema não executar nenhum débito nem crédito
    public void atualizarSaldo(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        if (contaOrigem != null) {
            if (contaOrigem.getClass().equals(ContaCorrente.class)
                    && contaOrigem.getSaldo().compareTo(valor) >= 0) {
                contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
            } else if (contaOrigem.getClass().equals(ContaEspecial.class)
                    && contaOrigem.getSaldo().add(((ContaEspecial) contaOrigem).getLimiteDeCreditoPreAprovado())
                        .compareTo(valor) >= 0) {
                contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
            } else {
                throw new ContaSemSaldoException();
            }
            contaRepository.saveAndFlush(contaOrigem);
        }
        if (contaDestino != null) {
            contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
            contaRepository.saveAndFlush(contaDestino);
        }
    }

}
