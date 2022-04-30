CREATE TABLE tb_movimentacao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_movimentacao DATETIME NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    valor DECIMAL(15,2) NOT NULL,
    cod_conta_destino BIGINT(20),
    cod_conta_origem BIGINT(20),
    FOREIGN KEY(cod_conta_destino) REFERENCES tb_conta(codigo),
    FOREIGN KEY(cod_conta_origem) REFERENCES tb_conta(codigo)
);
-- ENTRADA
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino) VALUES (UTC_TIMESTAMP, 'ENTRADA', 100, 1);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino) VALUES (UTC_TIMESTAMP, 'ENTRADA', 200, 2);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino) VALUES (UTC_TIMESTAMP, 'ENTRADA', 300, 3);
-- TRANSFERENCIA
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'TRANSFERENCIA', 20, 1, 2);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'TRANSFERENCIA', 230, 1, 3);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'TRANSFERENCIA', 100, 1, 2);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_destino, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'TRANSFERENCIA', 70, 2, 3);
-- SAIDA
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'SAIDA', 450, 1);
INSERT INTO tb_movimentacao (data_movimentacao, tipo_movimentacao, valor, cod_conta_origem) VALUES (UTC_TIMESTAMP, 'SAIDA', 150, 2);