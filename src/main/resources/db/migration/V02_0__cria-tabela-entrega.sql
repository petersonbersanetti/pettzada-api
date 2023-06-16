CREATE TABLE entrega(

	id BIGINT NOT NULL auto_increment,
    cliente_id BIGINT NOT NULL,
    taxa DECIMAL (10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_pedido datetime NOT NULL,
    data_finalizacao datetime,

    destinatario_nome VARCHAR(60) NOT NULL,
    destinatario_logradouro VARCHAR(255) NOT NULL,
    destinatario_numero VARCHAR(30) NOT NULL,
    destinatario_complemento VARCHAR(60),
    destinatario_bairro VARCHAR(30) NOT NULL,

    primary key (id)

);

ALTER TABLE entrega ADD CONSTRAINT fk_entrega_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente (id);
