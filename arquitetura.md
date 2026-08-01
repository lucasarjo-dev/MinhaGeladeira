Skip to content
lucasarjo-dev
MinhaGeladeira
Repository navigation
Code
Issues
Pull requests
Agents
Actions
Projects
Wiki
Security and quality
Insights
Settings
Files
Go to file
t
T
README.md
arquitetura.md
MinhaGeladeira
/
arquitetura.md
in
main

Edit

Preview
Indent mode

Spaces
Indent size

2
Line wrap mode

Soft wrap
Editing arquitetura.md file contents
  1
  2
  3
  4
  5
  6
  7
  8
  9
 10
 11
 12
 13
 14
 15
 16
 17
 18
 19
 20
 21
 22
 23
 24
 25
 26
 27
 28
 29
 30
 31
 32
 33
 34
 35
 36
 37
 38
 39
 40
 41
 42
 43
 44
 45
 46
 47
 48
 49
# Estrutura do Sistema

O sistema foi dividido em classes com responsabilidades específicas, então foram adicionados packages para organizar melhor.

## Índice

- [Conteúdo dos packages](#conteúdo-dos-packages)
  - [dao (Data Access Object)](#dao-data-access-object)
  - [database](#database)
  - [manager](#manager)
  - [model](#model)
  - [main](#main)

# Conteúdo dos packages

<img width="446" height="172" alt="image" src="https://github.com/user-attachments/assets/59200426-b741-4438-bdb1-447f90ad5c78" />

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
Use Control + Shift + m to toggle the tab key moving focus. Alternatively, use esc then tab to move to the next interactive element on the page.
Nenhum arquivo escolhido
Attach files by dragging & dropping, selecting or pasting them.
 
