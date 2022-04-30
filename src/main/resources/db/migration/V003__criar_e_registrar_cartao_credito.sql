CREATE TABLE tb_cartao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    validade DATE NOT NULL,
    cod_seguranca VARCHAR(3) NOT NULL,
    bandeira VARCHAR(20) NOT NULL,
    portador VARCHAR(50) NOT NULL,
    limite DECIMAL(10,2) NOT NULL,
    limite_disponivel DECIMAL(10,2) DEFAULT 0,
    cod_conta BIGINT(20) NOT NULL,
    CONSTRAINT fk_conta FOREIGN KEY (cod_conta) REFERENCES tb_conta (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tb_cartao(numero, cod_seguranca, bandeira, portador, limite, validade, cod_conta)
VALUES ('7896365896324569', '162', 'MasterCard', 'Rosa Maria Ferreira', 4000, '2025-12-31', 1);

INSERT INTO tb_cartao(numero, cod_seguranca, bandeira, portador, limite, validade, cod_conta)
VALUES ('1256987458963256', '259', 'MasterCard', 'José da Silva Gomes', 4000, '2025-12-31', 2);

INSERT INTO tb_cartao(numero, cod_seguranca, bandeira, portador, limite, validade, cod_conta)
VALUES ('4896523696322587', '369', 'MasterCard', 'Jucilei Rodiges Ferreira', 4000, '2025-12-31', 3);

INSERT INTO tb_cartao(numero, cod_seguranca, bandeira, portador, limite, validade, cod_conta)
VALUES ('1589741236952568', '325', 'MasterCard', 'Pretrolifera lopes LTDA', 4000, '2025-12-31', 4);

INSERT INTO tb_cartao(numero, cod_seguranca, bandeira, portador, limite, validade, cod_conta)
VALUES ('7458632565412359', '458', 'MasterCard', 'Bernado Gomes Pordutos Alimenticios', 4000, '2025-12-31', 5);