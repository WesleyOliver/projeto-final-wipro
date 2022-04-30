package br.edu.gama.gamaster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_movimentacao")
@Getter
@Setter
@ToString
public class Movimentacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;

    @CreationTimestamp
    @NotNull
    @Column(name="data_movimentacao")
    private LocalDateTime dataMovimentacao;

    @NotNull
    @Positive
    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cod_conta_origem")
    @JsonIdentityReference(alwaysAsId = true)
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "cod_conta_destino")
    @JsonIdentityReference(alwaysAsId = true)
    private Conta contaDestino;

}
