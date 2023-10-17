CREATE TABLE users (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    status bool NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    modified_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT pkey_users PRIMARY KEY (id)
);

CREATE TABLE address (
    id int8 PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT  NULL,
    gym_id int8,
    created_at TIMESTAMPTZ NOT NULL,
    modified_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE gym (
    id int8 PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    nome_academia VARCHAR(255),
    lotacao_media INT,
    endereco_sedes JSONB,
    created_at TIMESTAMPTZ NOT NULL,
    modified_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE hire (
    id int8 PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    nome_academia VARCHAR(255) NOT NULL,
    cnpj_academia VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    numero_telefone VARCHAR(255) NOT NULL,
    tipo_frequencia VARCHAR[],
    quantidade_academias INT[],
    created_at TIMESTAMPTZ NOT NULL,
    modified_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE cards (
    id int8 PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    nameHolder VARCHAR(255) NOT NULL,
    cardNumber VARCHAR(255) NOT NULL,
    flag VARCHAR(255) NOT NULL,
    dueDate VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    modified_at TIMESTAMPTZ NOT NULL
);
