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