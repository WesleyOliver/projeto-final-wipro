package br.edu.gama.gamaster.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GamasterExceptionHandlerContasInvalidas extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ContasInformadasInvalidasException.class})
    public ResponseEntity<Object> handleContasInformadasInvalidasException(ContasInformadasInvalidasException ex){
        String mensagemUsuario = messageSource.getMessage("conta.invalidas", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<GamasterExceptionHandler.Erro> erros = Arrays.asList(new GamasterExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ContaSemSaldoException.class})
    public ResponseEntity<Object> handleContaSemSaldoException(ContaSemSaldoException ex){
        String mensagemUsuario = messageSource.getMessage("conta.sem-saldo", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<GamasterExceptionHandler.Erro> erros = Arrays.asList(new GamasterExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
    
    @ExceptionHandler({CartaoExistenteException.class})
    public ResponseEntity<Object> handleContaSemSaldoException(CartaoExistenteException ex){
        String mensagemUsuario = messageSource.getMessage("cartao.existente", null,
                LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<GamasterExceptionHandler.Erro> erros = Arrays.asList(new GamasterExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
