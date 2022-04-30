CREATE TABLE tb_cliente(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf_cnpj VARCHAR(25) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(50),
    estado 	CHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Rosa Maria Ferreira', '809.193.210-38', '(62) 3859-6258', 'Av T9', '1730', 'Qd. 28 Lt. 19', 'Jardim America', '74250-200', 'Goiânia', 'GO');

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('José da Silva Gomes', '289.465.300-06', '(62) 98596-3256', 'Av T63', '158', 'Qd. A1 Lt. 8', 'Jardim Bela Vista', '74289-698', 'Goiânia', 'GO');

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Jucilei Rodiges Ferreira', '171.726.300-33', '(62) 3369-5896', 'Rua das Orquideas', 'S/N', 'Qd. 98 Lt. 52', 'Cidade Nova', '74287-896', 'Goiânia', 'GO');

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, bairro, cep, cidade, estado)
VALUES ('Pretrolifera lopes LTDA', '47.869.444/0001-21', '(11) 3658-5544', 'M. Tietê', '89', 'Novo Planalto', '01311-200', 'São Bernado do Campo', 'SP');

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Bernado Gomes Pordutos Alimenticios', '88.670.235/0001-31', '(99) 3698-8521', 'Rua dos Alpes', '185', 'Qd. 2 Lt. 1', 'Jardim Bela Morada', '12589-968', 'São Luiz', 'MA');

INSERT INTO tb_cliente(nome, cpf_cnpj, telefone, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES ('Gusttavo Lima Produtora LTDA', '63.254.635/0001-74', '(99) 3698-8521', 'Rua dos Alpes', '185', 'Qd. 2 Lt. 1', 'Jardim Bela Morada', '12589-968', 'São Luiz', 'MA');