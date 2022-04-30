package br.edu.gama.gamaster.service;

import br.edu.gama.gamaster.exceptionhandler.ContaSemSaldoException;
import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.ContaCorrente;
import br.edu.gama.gamaster.model.ContaEspecial;
import br.edu.gama.gamaster.model.dto.ContaDto;
import br.edu.gama.gamaster.repository.ContaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    void buscarTodasContas() {

        when(contaRepository.findAll()).thenReturn(new ArrayList<Conta>());

        List<Conta> contaList = contaService.buscarTodasContas();

        assertThat(contaList, notNullValue());
        assertThat(contaList, Matchers.isA(ArrayList.class));
        assertThat(contaList, hasSize(0));

    }

    @Test
    void buscarContaPeloCodigo(){
        Conta contaEncontrada = new ContaCorrente();
        contaEncontrada.setCodigo(1L);
        Long codigo = 1L;
        when(contaRepository.findById(codigo)).thenReturn(Optional.of(contaEncontrada));

        Conta conta = contaService.buscarPorCodigo(codigo);

        assertEquals(conta, contaEncontrada);

    }

    @Test
    void buscarContaPeloCodigo_excecaoContaNaoEncontrada(){
        Conta contaEncontrada = new ContaCorrente();
        contaEncontrada.setCodigo(1L);
        Long codigo = 1L;
        when(contaRepository.findById(codigo)).thenReturn(Optional.empty());

        assertThrows(EmptyResultDataAccessException.class,
                () -> contaService.buscarPorCodigo(codigo));

    }

    @Test
    void criarContaCorrente(){
        Boolean isContaEspecial = false;
        ContaDto contaDtoParaSalvar = new ContaDto("1234", "1589632-8", new Cliente());
        ContaCorrente contaCorrentePraSalvar = CriarContaCorrente.criarContaCorrente(contaDtoParaSalvar);
        ContaCorrente contaCorrenteSalva = CriarContaCorrente.criarContaCorrente(contaDtoParaSalvar);
        contaCorrenteSalva.setCodigo(1L);
        when(contaRepository.save(contaCorrentePraSalvar))
                .thenReturn(contaCorrenteSalva);
        Conta contaSalva = contaService.criarConta(isContaEspecial, contaDtoParaSalvar);

        assertEquals(ContaCorrente.class, contaSalva.getClass());
        assertEquals(1L, contaSalva.getCodigo());
        assertEquals(contaSalva.getTipoConta(), contaCorrenteSalva.getTipoConta());
    }

    @Test
    void criarContaEspecial(){
        Boolean isContaEspecial = true;
        ContaDto contaDtoParaSalvar = new ContaDto("1234", "1589632-8", new Cliente());
        ContaEspecial contaEspecialPraSalvar = CriarContaEspecial.criarContaEspecial(contaDtoParaSalvar);
        ContaEspecial contaEspecialSalva = CriarContaEspecial.criarContaEspecial(contaDtoParaSalvar);
        contaEspecialSalva.setCodigo(1L);
        when(contaRepository.save(contaEspecialPraSalvar))
                .thenReturn(contaEspecialSalva);
        Conta contaSalva = contaService.criarConta(isContaEspecial, contaDtoParaSalvar);

        assertEquals(ContaEspecial.class, contaSalva.getClass());
        assertEquals(1L, contaSalva.getCodigo());
        assertEquals(contaSalva.getTipoConta(), contaEspecialSalva.getTipoConta());
    }

    @Test
    void atualizarSaldo_transferenciaEntreDuasContasCorrente() {
        Conta contaOrigem = new ContaCorrente();
        contaOrigem.setSaldo(BigDecimal.valueOf(100L));
        Conta contaDestino = new ContaCorrente();
        contaDestino.setSaldo(BigDecimal.valueOf(50L));

        BigDecimal valorParaTransferir = BigDecimal.valueOf(40L);

        when(contaRepository.saveAndFlush(contaOrigem)).thenReturn(contaOrigem);
        when(contaRepository.saveAndFlush(contaDestino)).thenReturn(contaDestino);

        contaService.atualizarSaldo(contaOrigem, contaDestino, valorParaTransferir);

        assertThat(contaOrigem.getSaldo(), equalTo(BigDecimal.valueOf(60L)));
        assertThat(contaDestino.getSaldo(), equalTo(BigDecimal.valueOf(90L)));
        assertThat(contaDestino.getSaldo(), greaterThan(contaOrigem.getSaldo()));
    }

    @Test
    void atualizarSaldo_transferenciaEntreContaCorrenteEContaEspecial() {
        ContaEspecial contaOrigem = new ContaEspecial();
        contaOrigem.setSaldo(BigDecimal.valueOf(100L));
        contaOrigem.setLimiteDeCreditoPreAprovado(BigDecimal.valueOf(300L));
        Conta contaDestino = new ContaCorrente();
        contaDestino.setSaldo(BigDecimal.valueOf(50L));

        BigDecimal valorParaTransferir = BigDecimal.valueOf(200L);

        when(contaRepository.saveAndFlush(contaOrigem)).thenReturn(contaOrigem);
        when(contaRepository.saveAndFlush(contaDestino)).thenReturn(contaDestino);

        contaService.atualizarSaldo(contaOrigem, contaDestino, valorParaTransferir);

        assertThat(contaOrigem.getSaldo(), equalTo(BigDecimal.valueOf(-100L)));
        assertThat(contaDestino.getSaldo(), equalTo(BigDecimal.valueOf(250L)));
        assertThat(contaDestino.getSaldo(), greaterThan(contaOrigem.getSaldo()));
    }

    @Test()
    void atualizarSaldo_contaSemSaldo() {
        Conta contaOrigem = new ContaCorrente();
        contaOrigem.setSaldo(BigDecimal.valueOf(100L));
        Conta contaDestino = null;

        BigDecimal valorParaSacar = BigDecimal.valueOf(200L);

        ContaSemSaldoException contaSemSaldoException =
                assertThrows(ContaSemSaldoException.class,
                        () -> contaService.atualizarSaldo(contaOrigem, contaDestino, valorParaSacar));
    }
}