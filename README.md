# 📌 E-commerce API - Padrão arquiteturial MVC

Este projeto implementa operações de CRUD (Create, Read, Update, Delete) e as expõe por meio de uma API REST desenvolvida com **Spring Boot**. A arquitetura utilizada combina o padrão **MVC** com os princípios da **Clean Architecture**, garantindo um código modular, testável e de fácil manutenção.

O domínio escolhido para implementação foi **Produto**, e a aplicação utiliza um banco de dados relacional **MySQL**. Além disso, foram entregues os requisitos opcionais, incluindo a modelagem do banco de dados e um repositório com o código-fonte.

## 🗂️ Diagrama C4

<p align="center">
  <img src="https://sidroniolima.com.br/xpe/modulo-5/c4-1.png" alt="Diagrama C4 - System" width="800">
  <img src="https://sidroniolima.com.br/xpe/modulo-5/c4-2.png" alt="Diagrama C4 - Container" width="1200">
  </br>
  <img src="https://sidroniolima.com.br/xpe/modulo-5/c4-3.png" alt="Diagrama C4 - Component" width="1200">
  <img src="https://sidroniolima.com.br/xpe/modulo-5/c4-4.png" alt="Diagrama C4 - Code" width="1200">
</p>

## 📖 Tecnologias Utilizadas

- **API:** Java, Spring Boot, Spring Data JPA, Swagger
- **Banco de Dados:** MySQL
- **Containerização:** Docker, Docker Compose
- **Padrões:** Command

## 📑 Documentação da API

A API está documentada com **Swagger**. Para acessar a documentação interativa, suba a aplicação e acesse:

🔗 [Swagger UI](http://localhost:8080/swagger-ui/index.html)

## 🚀 Como Executar o Projeto

### 🏗️ Subindo com Docker

1. **Instale o Docker e Docker Compose** (se ainda não tiver instalado).
2. Clone este repositório:
   ```sh
   git clone https://github.com/sidroniolima/sidroniolima-xpe-arq-software-mod-5.git
   cd sidroniolima-xpe-arq-software-mod-5
   ```
3. **Suba os containers** do banco de dados e da API:
   ```sh
   docker-compose up --build
   ```
4. A API estará disponível em:
   ```
   http://localhost:8080/api/products
   ```

## 🛠️ Estrutura do Projeto

```
backend/
├── src/main/java/com/br/com/sidroniolima/xpe/
|   ├── configuration/ # Classe de configuração do framework SpringBoot
│   ├── controller/    # Controladores (exposição de endpoints)
│   ├── model/         # Entidades do domínio
│   ├── repository/    # Repositórios (acesso ao banco de dados)
│   ├── service/       # Lógica de negócio
│   ├── utils/         # Classes auxiliares
│   └── Main.java      # Classe principal para execução da aplicação
```

## ⛓ Padrão MVC

A estrutura de pastas do projeto segue o padrão arquitetural MVC (Model-View-Controller), que segmenta a aplicação em três camadas bem definidas. Essa separação contribui para uma melhor organização do código, facilita a manutenção, melhora a testabilidade e promove a responsabilidade modular dentro do sistema.

A seguir, a correlação entre as camadas do padrão MVC e os packages do projeto:

    **Controller** (package controller)
    As classes dentro desse package representam a camada de controle do padrão MVC. Elas são responsáveis por receber as requisições dos clientes, processar as entradas e encaminhá-las para os serviços apropriados.

    **Service** (package service)
    Essa camada contém a lógica de negócio da aplicação. Ela processa os dados recebidos dos controllers, aplica as regras de negócio e interage com a camada de modelo para atualizar o estado da aplicação.

    **Model** (package model)
    Representa a estrutura das entidades do domínio e o estado da aplicação. Essa camada define como os dados são modelados e manipulados internamente pelo sistema.

    **Repository** (package repository)
    Responsável pela persistência dos dados, geralmente interagindo com um banco de dados. Essa camada encapsula as operações de criação, leitura, atualização e exclusão (CRUD), garantindo um acesso estruturado e eficiente às informações.

    **View**
    No contexto deste projeto, a camada de visualização é representada pelas respostas em formato JSON retornadas pelos controllers às requisições feitas pelos clientes.

## 📄 Licença

Este projeto foi feito para fins educativo como atividade do módulo 5 do curso Arquitetura de Software da XPE.

---

Feito por [Sidronio Lima](https://github.com/sidroniolima) 🚀

