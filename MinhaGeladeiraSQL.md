## Instalação do banco

O banco de dados é responsável por armazenar os produtos da geladeira, categorias, estoque e receitas disponíveis.
Se deseja usar o mesmo banco que o MinhaGeladeira utiliza, execute o código abaixo no seu MySQL ou VS code:

ATENÇÃO: Execute o script SQL completo.  A ordem das tabelas deve ser mantida devido aos relacionamentos entre chaves estrangeiras.

    CREATE DATABASE MinhaGeladeira;

    USE MinhaGeladeira;


    CREATE TABLE categories(
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(20) NOT NULL
    );


    CREATE TABLE products (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        unit VARCHAR(10) NOT NULL,
        category_id INTEGER NOT NULL,

        FOREIGN KEY (category_id) REFERENCES categories(id)
    );


    CREATE TABLE stock (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        product_id INTEGER NOT NULL,
        quantity DECIMAL(10,2) NOT NULL,

        FOREIGN KEY (product_id) REFERENCES products(id)
    );


    CREATE TABLE recipe (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        description VARCHAR(2000),
        preparation_time VARCHAR(100),
        difficulty VARCHAR(100)
    );


    CREATE TABLE recipe_ingredient (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        recipe_id INTEGER NOT NULL,
        product_id INTEGER NOT NULL,
        quantity DECIMAL(10,2) NOT NULL,

        FOREIGN KEY (recipe_id) REFERENCES recipe(id),
        FOREIGN KEY (product_id) REFERENCES products(id)
    );


