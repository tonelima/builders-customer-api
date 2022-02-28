# builders-customer-api
Spring Rest API to Manage Customers

**Tecnologias utilizadas:**
- Java 8
- Spring Boot
- Spring Web
- Spring HATEOAS
- Lombok
- ModelMapper
- Validation
- Swagger
- JUnit
- Rest Assured
- PostgreSQL

**Visão Geral:**
API desenvolvida para gerenciamento de clientes, com oção de criar, alterar, listar todos, listar por paginação, listar por filtros e pesquisar pelo id.

**Detalhes do projeto:**
Utilizei a interface ControllerAdvice para capturar as exceções e dar uma resposta mais amigável no retorno da requisição.
Na criação do cliente, o campo "name" é obrigatório. Caso não seja informado, retornará um BAD_REQUEST (400) com detalhes na reposta. 
A alteração pode ser realizada de forma completa (todos os campos) ou de forma parcial (um ou mais campos). Caso não seja localizado o cliente com o "id" informado, retornará um NOT_FOUND (404) com detalhes na resposta.
A listagem de todos os clientes, retornará o erro NOT_FOUND (404) caso não tenha clientes cadastrados.
A listagem por paginação está definida com o padrão da interface Pageable, onde a página inicial é 0. Também retornará o erro NOT_FOUND (404) caso não tenha clientes cadastrados.
A listagem por filtros tem como opcionais todos os campos de pesquisa. Caso não seja informado nenhum campo, trará todos os clientes cadastrados. Caso o resultado da consulta seja vazio, irá retornar o erro NOT_FOUND (404).
A pesquisa pelo id irá retornar um cliente específico. Caso o id não corresponda a nenhum cliente no cadastro, retornará o erro NOT_FOUND (404).
As requisições de listagem e pesquisa individual utilizam o padrão HATEOAS. Os itens da listagem possuem link para o registro individual e a requisição do item individual possue link para a listagem completa.

**Requisições no Postman:**
https://www.getpostman.com/collections/9ca3bfd3776bdde74178
