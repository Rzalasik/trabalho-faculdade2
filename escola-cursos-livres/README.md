# Escola de Cursos Livres — MVC + JDBC + PostgreSQL

## CREATE TABLE SQL

Execute os comandos na ordem abaixo para respeitar as foreign keys:

```sql
CREATE TABLE aluno (
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    telefone  VARCHAR(20)
);

CREATE TABLE curso (
    id                 SERIAL PRIMARY KEY,
    nome               VARCHAR(100) NOT NULL,
    descricao          TEXT,
    carga_horaria      INT NOT NULL,
    vagas_totais       INT NOT NULL,
    vagas_disponiveis  INT NOT NULL
);

CREATE TABLE matricula (
    id          SERIAL PRIMARY KEY,
    aluno_id    INT            NOT NULL,
    curso_id    INT            NOT NULL,
    data        DATE           NOT NULL,
    valor_pago  NUMERIC(10, 2) NOT NULL CHECK (valor_pago >= 0),
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES curso(id),
    CONSTRAINT uq_matricula_aluno_curso UNIQUE (aluno_id, curso_id)
);
```

## Regras de Negócio

1. Não é permitido matricular um aluno em curso sem vagas disponíveis (`vagas_disponiveis > 0`) — lança `IllegalStateException`.
2. Não é permitido matricular o mesmo aluno duas vezes no mesmo curso — lança `IllegalStateException`.
3. O valor pago não pode ser negativo — lança `IllegalArgumentException` se `valor_pago < 0`.
4. Aluno e Curso devem estar cadastrados antes da matrícula — lança `IllegalArgumentException` se não encontrado.
5. Ao concluir uma matrícula com sucesso, `vagas_disponiveis` do curso é decrementado em 1.
6. É possível listar todos os alunos de um determinado curso.
7. É possível listar todos os cursos em que um determinado aluno está matriculado.

## Como Executar

```bash
# Compile o projeto
mvn compile

# Execute a classe Main
mvn exec:java
```

> Configure o banco de dados em `src/main/java/com/escola/util/Conexao.java`  
> (URL, usuário e senha do PostgreSQL).
