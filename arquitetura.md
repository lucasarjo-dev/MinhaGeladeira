# Conteúdo dos packages

<img width="446" height="172" alt="image" src="https://github.com/user-attachments/assets/59200426-b741-4438-bdb1-447f90ad5c78" />

## Índice

- [Conteúdo dos packages](#conteúdo-dos-packages)
  - [dao (Data Access Object)](#dao-data-access-object)
  - [database](#database)
  - [manager](#manager)
  - [model](#model)
  - [main](#main)

## dao (Data Access Object)
Os DAOs existem para separar o acesso ao banco de dados da lógica do código. Cada DAO é responsável por realizar operações de persistência para uma entidade específica do sistema.

`CategoryDAO` trabalha com objetos `Category` 

`ProductDAO`  trabalha com objetos `Product`

`StockDAO`    trabalha com objetos `Stock` 

`RecipeDAO`   trabalha com objetos `Recipe`

`RecipeIngredientDAO` trabalha com objetos `RecipeIngredient` 

Essas classes recebem objetos das entidades do sistema e utilizam seus atributos para executar operações no banco de dados. Dessa forma, as classes do modelo permanecem responsáveis apenas por representar os dados, enquanto os DAOs concentram toda a comunicação com o banco.

No MinhaGeladeira, todos os DAOs possuem os métodos `save()` e `findAll()`. Além deles, alguns DAOs implementam métodos específicos para atender às necessidades do sistema, como `updateQuantity()`, `findByProductId()`, `findByRecipeId()` e `populate()`.

O método `save()` recebe um objeto como parâmetro. Dentro do DAO, os atributos desse objeto são utilizados para preencher um PreparedStatement, que posteriormente executa a instrução SQL responsável por inserir os dados no banco de dados.

Conforme explicado anteriormente, o método save() recebe um objeto da entidade correspondente. Na implementação abaixo, o objeto Product fornece os valores utilizados para preencher o PreparedStatement, que posteriormente executa a instrução SQL responsável por persistir os dados no banco de dados.

Figura 1 – Implementação do método `save()` na classe `ProductDAO`.

<img width="866" height="292" alt="image" src="https://github.com/user-attachments/assets/a5468435-2db6-4e3f-a75c-bb68f3c81985" />



No caso do método `findAll()`, ele é o responsável por trazer os dados presentes nas tabelas das entidades do banco de dados para a aplicação.

O `findAll()` retorna um `ArrayList<>` de objetos da entidade correspondente. Para isso, é declarado um `PreparedStatement` contendo a instrução SQL que será executada. Em seguida, um `ResultSet` recebe o resultado da execução desse `PreparedStatement`.

Para armazenar os dados recebidos do banco de dados na aplicação, é utilizado um laço `while`, que se repete enquanto o método `next()` do `ResultSet` encontrar um novo registro. A cada iteração, um novo objeto da entidade correspondente é criado, e os valores das colunas retornadas pela consulta são obtidos através dos métodos `getString()`, `getInt()` e semelhantes. Esses valores são atribuídos aos atributos do objeto utilizando os métodos set.

Ao final de cada iteração, o objeto é adicionado ao `ArrayList`. Quando não existem mais registros para serem percorridos, o laço é encerrado e o método retorna a lista contendo todos os objetos recuperados do banco de dados.

Figura 2 – Implementação do método `findAll()` na classe `ProductDAO`

<img width="1102" height="662" alt="image" src="https://github.com/user-attachments/assets/127d6c52-2082-4813-a79b-04ac1b8d77a1" />


Sobre a funcionalidade dos outros métodos citados:

`updateQuantity()`

Atualiza a quantidade de um determinado produto no estoque. Esse método é utilizado após o preparo de uma receita, reduzindo a quantidade disponível do ingrediente correspondente no banco de dados.

`findByProductId()`

Realiza uma consulta utilizando o identificador (id) de um produto e retorna o registro correspondente no estoque. Esse método é utilizado para verificar a quantidade disponível de um ingrediente antes de preparar uma receita.

`findByRecipeId()`

Consulta todos os ingredientes associados a uma determinada receita por meio do seu identificador (recipe_id). O método retorna uma lista contendo todos os ingredientes necessários para o preparo da receita selecionada.

`populate()`

Método utilizado apenas durante o desenvolvimento do projeto para inserir dados iniciais no banco de dados de forma automática. Sua finalidade é facilitar os testes do sistema, evitando o cadastro manual de receitas, ingredientes ou itens do estoque a cada execução.

## database

A classe `DatabaseConnection` é responsável por centralizar a criação da conexão com o banco de dados.

Nela são armazenadas as informações necessárias para a conexão, como a URL do banco, o usuário e a senha. O método `getConnection()` utiliza o `DriverManager` da biblioteca JDBC para estabelecer a conexão e retornar um objeto do tipo `Connection`, que posteriormente é utilizado pelos DAOs para executar instruções SQL.

No MinhaGeladeira, todas as classes DAO obtêm a conexão chamando:

`Connection conn = DatabaseConnection.getConnection();`

Dessa forma, a lógica de conexão permanece concentrada em uma única classe, evitando repetição de código e facilitando futuras alterações nas configurações do banco de dados.

## manager

A classe `RecipeManager` contém a lógica responsável pelo preparo das receitas. Antes de descontar os ingredientes do estoque, o método `prepareRecipe()` utiliza `canPrepareRecipe()` para verificar se todos os ingredientes necessários estão disponíveis em quantidade suficiente. Caso algum ingrediente esteja indisponível, o método interrompe sua execução imediatamente utilizando `return`, impedindo que alterações incorretas sejam realizadas no estoque. 
Se todas as verificações forem satisfeitas, as quantidades dos ingredientes são atualizadas através do método `updateQuantity()` da classe `StockDAO`.

## model

As classes do package model representam as entidades do sistema. Elas são responsáveis por armazenar os atributos de cada entidade e disponibilizar métodos `get` e `set` para acessar e modificar esses dados.

No MinhaGeladeira, as entidades são:

`Category` – representa uma categoria de produtos.
`Product` – representa um produto cadastrado na geladeira.
`Stock` – representa a quantidade disponível de cada produto no estoque.
`Recipe` – representa uma receita cadastrada no sistema.
`RecipeIngredient` – representa a relação entre uma receita e os ingredientes necessários para seu preparo.

Essas classes não possuem responsabilidade de acessar o banco de dados. Sua função é apenas representar os dados utilizados pela aplicação, sendo posteriormente manipulados pelos DAOs e pelas classes de serviço.

## main

A classe `Main` é o ponto de entrada da aplicação. Ela é responsável por iniciar a execução do sistema, apresentar o menu de opções ao usuário e controlar o fluxo da aplicação.

Por meio de um laço `while`, o sistema permanece em execução até que o usuário escolha a opção de encerramento. A opção selecionada é tratada por uma estrutura `switch`, que direciona a execução para a funcionalidade correspondente.
