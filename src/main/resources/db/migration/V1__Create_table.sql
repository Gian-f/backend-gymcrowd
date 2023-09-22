CREATE TABLE users (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    status bool NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    CONSTRAINT pkey_users PRIMARY KEY (id)
);

CREATE TABLE address (
    id int8 PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    gym_id int8,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);

CREATE TABLE gym (
    id int8 PRIMARY KEY,
    nome_academia VARCHAR(255),
    lotacao_media INT,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);

CREATE TABLE hire (
    id int8 PRIMARY KEY,
    nome_academia VARCHAR(255) NOT NULL,
    cnpj_academia VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    numero_telefone VARCHAR(255) NOT NULL,
    tipo_frequencia VARCHAR[],
    quantidade_academias INT[],
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL
);
