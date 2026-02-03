# Gerenciador de Usuários - API REST com Spring Boot e JWT

API REST desenvolvida com **Java e Spring Boot**, seguindo o padrão de arquitetura em camadas,  
com autenticação e autorização utilizando **JWT (JSON Web Token)**.

O sistema permite o cadastro, autenticação e gerenciamento completo de usuários,  
incluindo atualização parcial de dados, validação de informações e proteção de rotas.

---

## 🛠 Tecnologias utilizadas

- Java 17  
- Spring Boot  
- Spring Web (REST API)  
- Spring Security + JWT  
- Spring Data JPA + Hibernate  
- Bean Validation  
- PostgreSQL  
- Maven  

---

## ⚙️ Funcionalidades

- Cadastro de usuários  
- Autenticação com JWT  
- Listagem de usuários  
- Busca por ID e filtros  
- Atualização completa (PUT)  
- Atualização parcial (PATCH)  
- Exclusão de usuários  
- Validação de dados de entrada  
- Proteção de endpoints com Spring Security  

---

## 🔗 Endpoints principais

### 🔐 Autenticação

- `POST /auth/register` – Cadastra um novo usuário  
- `POST /auth/login` – Autentica o usuário e retorna o token JWT  

### 👤 Usuários

- `GET /users` – Lista todos os usuários  
- `GET /users/{id}` – Busca usuário por ID  
- `GET /users/search` – Busca usuários por parâmetros  
- `PUT /users/{id}` – Atualiza todos os dados do usuário  
- `PATCH /users/{id}` – Atualiza parcialmente os dados do usuário 
- `DELETE /users/{id}` – Remove usuário  

---

## 🔐 Autenticação e Autorização

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

### Fluxo de autenticação:

1. Faça login em `/auth/login`  
2. Copie o token JWT retornado  
3. Envie o token no header das requisições protegidas:

```
Authorization: Bearer SEU_TOKEN_AQUI
```


### Possíveis respostas:

- **401 Unauthorized** → Usuário não autenticado  
- **403 Forbidden** → Usuário sem permissão  

---

## 🗂 Estrutura do Projeto
A aplicação segue o padrão de arquitetura em camadas, facilitando manutenção, organização e escalabilidade.


- controller → Endpoints REST
- service → Regras de negócio
- repository → Acesso ao banco de dados
- dto → Transferência de dados entre camadas
- entity → Entidades JPA mapeadas para o banco de dados
- security → Configurações de autenticação, autorização e JWT
- config → Configurações gerais da aplicação (beans, segurança, etc.)
- exception → Tratamento global de exceções e erros personalizados

---

## ▶️ Como executar o projeto

### Pré-requisitos

- Java 17  
- Maven  
- PostgreSQL

> ⚠ Certifique-se de que o PostgreSQL esteja em execução e que o banco de dados esteja criado antes de iniciar a aplicação.

---

### Passos para execução

```bash
# Clone o repositório
git clone https://github.com/gabriel-kobayashi/gerenciador-de-usuarios.git

# Acesse a pasta do projeto
cd gerenciador-de-usuarios

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em:
http://localhost:8080

---

## ⚙️ Configurações do Banco de Dados
As configurações de conexão com o banco podem ser alteradas no arquivo:

- src/main/resources/application.properties

Principais propriedades configuráveis:

- URL do banco
- Usuário
- Senha
- Porta
