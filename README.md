# Pharmadrug

Sistema de e-commerce para uma farmácia, desenvolvido como projeto da disciplina de LPR2.

O projeto utiliza Java, Spring Boot, Vaadin e SQLite. A aplicação possui uma interface web para visualização de produtos e, futuramente, funcionalidades de clientes, compras e gerenciamento de produtos.

## Tecnologias

- Java 25
- Spring Boot
- Vaadin
- SQLite
- Maven
- JDBC

## Estrutura do projeto

O projeto está organizado separando as responsabilidades da aplicação:

- `config` → configurações da aplicação e banco de dados
- `model` → classes que representam os dados do sistema
- `repository` → acesso ao banco de dados
- `service` → regras de negócio
- `view` → telas e componentes da interface Vaadin
- `database` → banco SQLite e scripts SQL

## Como executar

### 1. Pré-requisitos

Antes de executar o projeto, tenha instalado:

- JDK 25
- IntelliJ IDEA (ou outra IDE compatível com Java)
- Git

O Maven não precisa ser instalado separadamente, pois o projeto utiliza o Maven Wrapper (`mvnw`).

### 2. Clonar o projeto

Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```
### 3. Abrir o Projeto

Abra a pasta do projeto na sua IDE.

No IntelliJ IDEA, aguarde o Maven carregar as dependências do projeto.

### 4. Executar a aplicação

- Pela IDE
```
src/main/java/com/pharmacy/Application.java
```

