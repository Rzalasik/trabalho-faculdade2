# Oficina Mecânica — MVC + JDBC + PostgreSQL

## CREATE TABLE SQL

Execute os comandos na ordem abaixo para respeitar as foreign keys:

```sql
CREATE TABLE cliente (
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    telefone  VARCHAR(20)
);

CREATE TABLE veiculo (
    id          SERIAL PRIMARY KEY,
    placa       VARCHAR(10)  NOT NULL UNIQUE,
    modelo      VARCHAR(100) NOT NULL,
    ano         INT,
    cliente_id  INT NOT NULL,
    CONSTRAINT fk_veiculo_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE ordem_servico (
    id                  SERIAL PRIMARY KEY,
    veiculo_id          INT            NOT NULL,
    descricao_problema  TEXT           NOT NULL,
    valor               NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    status              VARCHAR(10)    NOT NULL DEFAULT 'ABERTA',
    CONSTRAINT fk_os_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    CONSTRAINT chk_status CHECK (status IN ('ABERTA', 'CONCLUIDA'))
);
```

## Regras de Negócio

1. Não é permitido abrir uma Ordem de Serviço para um veículo que não esteja cadastrado no sistema.
2. O valor da Ordem de Serviço não pode ser negativo — lança `IllegalArgumentException` se `valor < 0`.
3. É possível consultar o histórico completo de manutenções (OSs) de um determinado veículo.
4. O status da OS pode ser alterado de `ABERTA` para `CONCLUIDA`.

## Como Executar

```bash
# Compile o projeto
mvn compile

# Execute a classe Main
mvn exec:java
```

> Configure o banco de dados em `src/main/java/com/oficina/util/Conexao.java`  
> (URL, usuário e senha do PostgreSQL).
