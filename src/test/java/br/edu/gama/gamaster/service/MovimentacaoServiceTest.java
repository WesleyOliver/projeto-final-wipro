package br.edu.gama.gamaster.service;

import br.edu.gama.gamaster.model.Cliente;
import br.edu.gama.gamaster.model.Conta;
import br.edu.gama.gamaster.model.Movimentacao;
import br.edu.gama.gamaster.model.TipoMovimentacao;
import br.edu.gama.gamaster.model.dto.ContaDto;
import br.edu.gama.gamaster.model.dto.MovimentacaoSaqueDto;
import br.edu.gama.gamaster.repository.MovimentacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private MovimentacaoService movimentacaoService;

    @Test
    public void buscandoMovimentacaoPorConta_deveRetornarUmaListaDeMovimentacao() {
        ContaDto contaDto = new ContaDto("1234", "1589632-8", new Cliente());
        Conta conta = CriarContaCorrente.criarContaCorrente(contaDto);
        conta.setCodigo(1L);

        when(contaService.buscarPorCodigo(1L))
                .thenReturn(conta);

        when(movimentacaoRepository.findByContaOrigemOrContaDestino(conta, conta))
                .thenReturn(new ArrayList<Movimentacao>());

        List<Movimentacao> movimentacaoList = movimentacaoService.buscarMovimentacaoPorConta(1L);

        assertEquals(ArrayList.class, movimentacaoList.getClass());
    }

    @Test
    public void buscandoMovimentacaoEntreContas_deveRetornarUmaListaDeMovimentacao() {
        ContaDto contaOrigemDto = new ContaDto("1234", "1589632-8", new Cliente());
        Conta contaOrigem = CriarContaCorrente.criarContaCorrente(contaOrigemDto);
        contaOrigem.setCodigo(1L);
        ContaDto contaDestinoDto = new ContaDto("1234", "1589632-8", new Cliente());
        Conta contaDestino = CriarContaCorrente.criarContaCorrente(contaDestinoDto);
        contaDestino.setCodigo(2L);
        List<Movimentacao> movimentacaoListToReturn = new ArrayList<>();
        movimentacaoListToReturn.add(new Movimentacao());
        movimentacaoListToReturn.add(new Movimentacao());
        movimentacaoListToReturn.add(new Movimentacao());

        when(contaService.buscarPorCodigo(1L)).thenReturn(contaOrigem);
        when(contaService.buscarPorCodigo(2L)).thenReturn(contaDestino);

        when(movimentacaoRepository.findByContaOrigemAndContaDestino(contaOrigem, contaDestino))
                .thenReturn(movimentacaoListToReturn);

        List<Movimentacao> movimentacaoList = movimentacaoService.buscarMovimentacaoEntreContas(1L, 2L);

        assertEquals(movimentacaoList.size(),3);
        assertEquals(movimentacaoList.getClass(), ArrayList.class);
    }

    @Test
    public void buscandoEntradasPorConta_deveRetornarUmaListaDeMovimentacao() {
        when(movimentacaoRepository.buscarEntradasPorConta(1L))
                .thenReturn(new ArrayList<Movimentacao>());

        List<Movimentacao> movimentacaoList = movimentacaoService.buscarEntradasPorConta(1L);
        assertThat(movimentacaoList, notNullValue());
        assertEquals(ArrayList.class, movimentacaoList.getClass());
    }

    @Test
    public void buscandoSaidasPorConta_deveRetornarUmaListaDeMovimentacao() {
        when(movimentacaoRepository.buscarSaidasPorConta(1L))
                .thenReturn(new ArrayList<Movimentacao>());

        List<Movimentacao> movimentacaoList = movimentacaoService.buscarSaidasPorConta(1L);
        assertThat(movimentacaoList, notNullValue());
        assertEquals(ArrayList.class, movimentacaoList.getClass());
    }


}