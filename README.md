# Trabalho 2 — MVC, JDBC e PostgreSQL

Três sistemas desenvolvidos em Java utilizando o padrão **MVC**, **JDBC puro** e banco de dados **PostgreSQL**. Cada projeto é independente e demonstra o fluxo completo de uma aplicação com regras de negócio, acesso a dados e separação de responsabilidades em camadas.

---

## Projetos

| Projeto | Branch | Descrição |
|---------|--------|-----------|
| [Clínica Veterinária](clinica-veterinaria/) | `cenário1` | Gestão de tutores, animais e consultas |
| [Oficina Mecânica](oficina-mecanica/) | `cenário2` | Gestão de clientes, veículos e ordens de serviço |
| [Escola de Cursos Livres](escola-cursos-livres/) | `cenário3` | Gestão de alunos, cursos e matrículas |

---

## Arquitetura

Todos os projetos seguem a mesma estrutura MVC:

```
src/main/java/com/<projeto>/
├── Main.java              ← Ponto de entrada — demonstra o fluxo completo
├── model/                 ← Entidades (POJOs)
├── repository/            ← Acesso ao banco via JDBC puro (PreparedStatement)
├── service/               ← Regras de negócio
├── controller/            ← Coordena Service e exibe resultados
└── util/
    └── Conexao.java       ← Configuração da conexão JDBC
```

### Fluxo de chamadas

```
Main → Controller → Service → Repository → PostgreSQL
```

---

## Tecnologias

| Ferramenta | Versão |
|------------|--------|
| Java (JDK) | 17     |
| Maven      | 3.8+   |
| PostgreSQL  | 16     |
| JDBC Driver | 42.x   |

---

## Como executar

### 1. Suba o banco de dados

```bash
bash setup.sh
```

O script cria o container PostgreSQL, instala as dependências e cria os 3 bancos automaticamente.

### 2. Execute cada projeto no IntelliJ IDEA

1. `File → Open` → selecione a pasta do projeto (`clinica-veterinaria`, `oficina-mecanica` ou `escola-cursos-livres`)
2. Aguarde o Maven baixar as dependências
3. Abra `Main.java` e clique em **▶ Run**

### 3. Ou via terminal (Maven)

```bash
cd clinica-veterinaria
mvn compile exec:java
```

---

## Bancos de dados

| Banco | Projeto |
|-------|---------|
| `d_clinica_veterinaria` | Clínica Veterinária |
| `d_oficina_mecanica` | Oficina Mecânica |
| `d_escola_cursos` | Escola de Cursos Livres |

Conexão padrão: `localhost:5432` · usuário: `postgres` · senha: `postgres`
