# Clínica Veterinária — MVC + JDBC + PostgreSQL

## CREATE TABLE SQL

Execute os comandos na ordem abaixo para respeitar as foreign keys:

```sql
CREATE TABLE tutor (
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    endereco  VARCHAR(200),
    telefone  VARCHAR(20)
);

CREATE TABLE animal (
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    especie   VARCHAR(50)  NOT NULL,
    raca      VARCHAR(50),
    tutor_id  INT NOT NULL,
    CONSTRAINT fk_animal_tutor FOREIGN KEY (tutor_id) REFERENCES tutor(id)
);

CREATE TABLE consulta (
    id         SERIAL PRIMARY KEY,
    animal_id  INT            NOT NULL,
    data       DATE           NOT NULL,
    motivo     VARCHAR(200)   NOT NULL,
    valor      NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    CONSTRAINT fk_consulta_animal FOREIGN KEY (animal_id) REFERENCES animal(id)
);
```

## Regras de Negócio

1. Não é permitido registrar uma consulta para um animal que não esteja cadastrado no sistema.
2. O valor da consulta não pode ser negativo — lança `IllegalArgumentException` se `valor < 0`.
3. É possível consultar todas as consultas realizadas para um determinado animal.
4. É possível listar todos os animais vinculados a um determinado tutor.

## Como Executar

```bash
# Compile o projeto
mvn compile

# Execute a classe Main
mvn exec:java
```

> Configure o banco de dados em `src/main/java/com/clinica/util/Conexao.java`  
> (URL, usuário e senha do PostgreSQL).
