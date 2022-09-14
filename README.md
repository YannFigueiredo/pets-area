# Pets Area
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/YannFigueiredo/pets-area/blob/main/LICENSE)

# Sobre o projeto

Aplicação web com um CRUD para registros anmais de estimação dos usuários. A aplicação contém diversos filtros e opções de ordenação para tratar os registros vindos do banco de dados MySQL.

*** Autenticação e front-end em desenvolvimento ***

## Modelo conceitual
<img src="https://github.com/YannFigueiredo/assets/blob/main/pets-area/modelo-conceitual.png"  alt="Modelo conceitual" title="Modelo conceitual"/>

## Documentação da API
[link swagger]

# Tecnologias utilizadas
## Back end

- Java
- Spring Boot
- JPA / Hibernate
- Maven
- MySQL
- Swagger
- Docker

## Front end
- Em desenvolvimento

## Implantação em produção
- Back end: Heroku
- Front end: Netlify
- Banco de dados: MySQL

# Como executar o projeto

## Back end
Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/YannFigueiredo/pets-area
# entrar na pasta do projeto back end
cd backend
# executar o projeto
./mvnw spring-boot:run
```

## Front end
Pré-requisitos: npm / yarn  e NodeJs

```bash
# clonar repositório
git clone https://github.com/YannFigueiredo/petsarea
# entrar na pasta do projeto front end
cd frontend
# instalar dependências
npm install
# executar o projeto
npm start run
```
## Projeto completo com Docker
Pré-requisitos: Docker

```bash
# clonar repositório
git clone https://github.com/YannFigueiredo/petsarea
# entrar na pasta do projeto petsarea
cd petsarea
# executar Docker
docker-compose up -d
```

# Autor

Yann Fabricio Cardoso de Figueiredo

https://www.linkedin.com/in/yannfigueiredo/
